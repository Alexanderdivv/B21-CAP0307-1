package com.project1.fatigueapplication.view

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.project1.fatigueapplication.R
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    companion object {
        private val PERMISSION_CODE = 400
        private val CAMERA_CAPTURE_CODE = 401
        private val GALLERY_PICK_CODE = 402
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.buttonmb)
        val text: TextView = findViewById(R.id.textViewmb)
        val string0: String= getString(R.string.identify)

        button.setOnClickListener {
            text.setText(string0)
            Toast.makeText(this,string0, Toast.LENGTH_SHORT).show()
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
            val picture = data?.getParcelableExtra<Bitmap>("data")
            imageView.setImageBitmap(picture)
        }
        else if(resultCode == Activity.RESULT_OK && requestCode == GALLERY_PICK_CODE){
            imageView.setImageURI(data?.data)
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