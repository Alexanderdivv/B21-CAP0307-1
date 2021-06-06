package com.project1.fatigueapplication.view

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.get
import androidx.viewpager2.widget.ViewPager2
import com.project1.githubsub2.R
import com.project1.githubsub2.adapter.IntroSliderAdapter
import com.project1.githubsub2.databinding.ActivityUserIntroBinding
import com.project1.githubsub2.model.data.IntroSlide
import com.project1.githubsub2.view.activity.MainGitHubActivity

class IntroGitHubActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserIntroBinding.inflate(layoutInflater)

        setContentView(binding.root)
        binding.isIntroSliderViewPager2.adapter = introSliderAdapter

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setIcon(R.drawable.ic_github_full_icon)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val window = this.window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.statusBarColor = this.resources.getColor(R.color.green)
        window.navigationBarColor = this.resources.getColor(R.color.green)

        setupIndicators()
        setCurrentIndicator(0)
        binding.isIntroSliderViewPager2.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        binding.btnNext.setOnClickListener {
            if (binding.isIntroSliderViewPager2.currentItem + 1 < introSliderAdapter.itemCount) {
                binding.isIntroSliderViewPager2.currentItem += 1
            } else {
                Intent(applicationContext, MainGitHubActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        binding.tvSkipIntro.setOnClickListener {
            Intent(applicationContext, MainGitHubActivity::class.java).also {
                startActivity(it)
            }
        }
    }

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Hello World",
                "Meet developers from around the world",
                R.drawable.ic_intro1
            ),
            IntroSlide(
                "Get To Know",
                "Learn new things with lot of sources",
                R.drawable.ic_intro2
            ),
            IntroSlide(
                "Build Together",
                "Create a new things together",
                R.drawable.ic_intro3
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
