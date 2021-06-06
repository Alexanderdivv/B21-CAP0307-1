package com.project1.fatigueapplication.view.Intro

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.project1.fatigueapplication.R
import com.project1.fatigueapplication.adapter.IntroSliderAdapter
import com.project1.fatigueapplication.data.IntroSlide
import com.project1.fatigueapplication.databinding.ActivityIntroBinding
import com.project1.fatigueapplication.view.activity.MainActivity

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)

        val string0: String= getString(R.string.introslider)
        Toast.makeText(this,string0, Toast.LENGTH_SHORT).show()

        setContentView(binding.root)
        binding.isIntroSliderViewPager2.adapter = introSliderAdapter

        setupIndicators()
        setCurrentIndicator(0)
        binding.isIntroSliderViewPager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        binding.tvSkipIntro.setOnClickListener {
            Intent(applicationContext, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "B21-CAP0307",
                "Bangkit Capstone Projects",
                R.drawable.bangkit
            ),
            IntroSlide(
                "Capture or Add Photo",
                "Analyze your conditions using photo",
                R.drawable.ic_smile
            ),
            IntroSlide(
                "Start Live Screening",
                "Observe your conditions in real-time",
                R.drawable.ic_smile
            )
        )
    )

    private fun setCurrentIndicator(index: Int) {
        val childCount = this.binding.icIndicatorsContainer.childCount
        for (i in 0 until childCount) {
            val imageView2 = this.binding.icIndicatorsContainer[i] as ImageView
            if (i == index) {
                imageView2.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_intro_indicator_active_icon
                    )
                )
            } else {
                imageView2.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_intro_indicator_inactive_icon
                    )
                )
            }
        }
    }

    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(8, 0, 8, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.ic_intro_indicator_inactive_icon
                    )
                )
                this?.layoutParams = layoutParams
            }
            binding.icIndicatorsContainer.addView(indicators[i])
        }
    }
}
