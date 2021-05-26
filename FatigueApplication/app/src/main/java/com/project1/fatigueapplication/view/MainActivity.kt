package com.project1.fatigueapplication.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.project1.fatigueapplication.R

class MainActivity : AppCompatActivity() {
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val string1: String= getString(R.string.setting)
        val string2: String= getString(R.string.team)
        when (item.itemId) {
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