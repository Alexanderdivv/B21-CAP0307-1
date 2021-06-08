package com.project1.fatigueapplication.view.activity

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.project1.fatigueapplication.R
import com.project1.fatigueapplication.data.api.Resource
import com.project1.fatigueapplication.databinding.ActivityMainBinding
import com.project1.fatigueapplication.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    companion object {
        private const val PERMISSION_CODE = 400
        private const val CAMERA_CAPTURE_CODE = 401
        private const val GALLERY_PICK_CODE = 402
    }

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)

        val string: String= getString(R.string.main)
        Toast.makeText(this,string, Toast.LENGTH_SHORT).show()

        val string0: String= getString(R.string.identify)

        binding.buttonmb.setOnClickListener {
            binding.textViewmb.setText(string0)
            Toast.makeText(this,string0, Toast.LENGTH_SHORT).show()

        }
        binding.imageView.setOnClickListener {
            if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) !=PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), PERMISSION_CODE)
            }

            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(intent, CAMERA_CAPTURE_CODE)
        }
        viewModel.uploadPhoto.observe(this) {
            when (it) {
                is Resource.Success -> {
                    binding.progressCircular.visibility = View.GONE
                }
                is Resource.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
                is Resource.Error -> {
                    binding.progressCircular.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        val imageView : ImageView = findViewById(R.id.imageView)

        if(requestCode == CAMERA_CAPTURE_CODE) {
            val picture = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(picture)
            viewModel.upload(picture)
        }
        else if(resultCode == Activity.RESULT_OK && requestCode == GALLERY_PICK_CODE){
            imageView.setImageURI(data?.data)
            val picture = data?.extras?.get("ImageURI") as Bitmap
            viewModel.upload(picture)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val string1: String= getString(R.string.setting)
        val string2: String= getString(R.string.team)
        when (item.itemId) {
            R.id.gallery -> {
                if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE) !=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE), PERMISSION_CODE)
                }
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent, GALLERY_PICK_CODE)
            }
            R.id.camera -> {
                if(ActivityCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) !=PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.CAMERA), PERMISSION_CODE)
                }

                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent, CAMERA_CAPTURE_CODE)
            }
            R.id.setting_option_menu -> {
                Intent(this, OptionActivity::class.java).also {
                    startActivity(it)
                    Toast.makeText(this,string1, Toast.LENGTH_SHORT).show()
                }
            }
            R.id.our_team_option_menu -> {
                Intent(this, OurteamActivity::class.java).also {
                    startActivity(it)
                    Toast.makeText(this, string2, Toast.LENGTH_SHORT).show()
                }
            }
        }
        return true
    }
}