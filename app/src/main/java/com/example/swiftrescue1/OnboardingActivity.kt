package com.example.swiftrescue1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2

class OnboardingActivity : AppCompatActivity() {

    private lateinit var viewPager: ViewPager2
    private lateinit var btnNext: Button
    private lateinit var btnSkip: Button
    private lateinit var dotsIndicator: LinearLayout

    private val onboardingScreens = listOf(
        OnboardingModel(R.drawable.onboarding1, "Welcome", "Swift Rescue - Your roadside assistance partner."),
        OnboardingModel(R.drawable.onboarding2, "Real-time Tracking", "Track your rescue team in real-time."),
        OnboardingModel(R.drawable.onboarding3, "Seamless Service", "Quick and reliable vehicle breakdown assistance.")
    )

    private lateinit var adapter: OnboardingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        // Initialize views
        viewPager = findViewById(R.id.viewPagerOnboarding)
        btnNext = findViewById(R.id.btnNext)
        btnSkip = findViewById(R.id.btnSkip)
        dotsIndicator = findViewById(R.id.dotsIndicator)

        // Setup ViewPager Adapter
        adapter = OnboardingAdapter(onboardingScreens)
        viewPager.adapter = adapter

        // Setup Dots Indicator
        setupDotsIndicator()
        updateDots(0)

        // ViewPager change listener
        viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateDots(position)
                if (position == onboardingScreens.size - 1) {
                    btnNext.text = "Get Started"
                } else {
                    btnNext.text = "Next"
                }
            }
        })

        // Next Button Click Listener
        btnNext.setOnClickListener {
            if (viewPager.currentItem < onboardingScreens.size - 1) {
                viewPager.currentItem += 1
            } else {
                navigateToHome()
            }
        }

        // Skip Button Click Listener
        btnSkip.setOnClickListener {
            navigateToHome()
        }
    }

    private fun setupDotsIndicator() {
        dotsIndicator.removeAllViews()
        for (i in onboardingScreens.indices) {
            val dot = TextView(this).apply {
                text = "●"
                textSize = 14f
                setTextColor(ContextCompat.getColor(this@OnboardingActivity, R.color.black))
                setPadding(8, 0, 8, 0)
            }
            dotsIndicator.addView(dot)
        }
    }

    private fun updateDots(position: Int) {
        for (i in 0 until dotsIndicator.childCount) {
            val dot = dotsIndicator.getChildAt(i) as TextView
            dot.setTextColor(
                if (i == position) ContextCompat.getColor(this, R.color.primaryColor)
                else ContextCompat.getColor(this, R.color.gray)
            )
        }
    }

    private fun navigateToHome() {
        startActivity(Intent(this, RegisterActivity::class.java))
        finish()
    }
}