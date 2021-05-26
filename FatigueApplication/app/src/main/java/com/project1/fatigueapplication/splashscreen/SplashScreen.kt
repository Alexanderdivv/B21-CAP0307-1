package com.project1.fatigueapplication.splashscreen

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.project1.fatigueapplication.R
import com.project1.fatigueapplication.view.MainActivity

class SplashScreen : AppCompatActivity() {

    private lateinit var topAnimation: Animation
    private lateinit var bottomAnimation: Animation
    private lateinit var imageIconLogo: ImageView
    private lateinit var textViewOpening: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        val string0: String= getString(R.string.splashscreen)
        Toast.makeText(this,string0, Toast.LENGTH_SHORT).show()
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)

        topAnimation = AnimationUtils.loadAnimation(this, R.anim.top_animations_panel)
        bottomAnimation = AnimationUtils.loadAnimation(this, R.anim.bot_animations_panel)

        imageIconLogo = findViewById(R.id.head_icon)
        textViewOpening = findViewById(R.id.opening_text)

        imageIconLogo.startAnimation(topAnimation)
        textViewOpening.startAnimation(bottomAnimation)
    }
}