package com.toyibnurseha.news_mvvm.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import com.github.appintro.AppIntroPageTransformerType
import com.github.appintro.indicator.DotIndicatorController
import com.toyibnurseha.news_mvvm.R
import kotlinx.android.synthetic.main.items_article_preview.*

class OnBoardingActivity : AppIntro() {

    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPref = getSharedPreferences("news_pref", Context.MODE_PRIVATE)

        val isOnboardPassed = sharedPref.getBoolean("isOnboardPassed", false)

        if (isOnboardPassed)
            startActivity(Intent(this, NewsActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })

        showStatusBar(false)
        isIndicatorEnabled = true
        setProgressIndicator()
        indicatorController = DotIndicatorController(this)
        setIndicatorColor(
            selectedIndicatorColor = R.color.black,
            unselectedIndicatorColor = R.color.white
        )

        setTransformer(AppIntroPageTransformerType.Parallax(
            titleParallaxFactor = 1.0,
            imageParallaxFactor = -1.0,
            descriptionParallaxFactor = 2.0
        ))

        addSlide(AppIntroFragment.createInstance(
            title = "Top Trending News",
            description = "Check the current top trending issues",
            imageDrawable = R.drawable.ic_newspaper,
            titleColorRes = R.color.cornflower_blue,
            descriptionColorRes = R.color.black,
        ))

        addSlide(AppIntroFragment.createInstance(
            title = "Easy Access",
            description = "Access the hot news within your smartphone",
            imageDrawable = R.drawable.ic_mobile_news,
            titleColorRes = R.color.cornflower_blue,
            descriptionColorRes = R.color.black,
        ))

        addSlide(AppIntroFragment.createInstance(
            title = "Countless Resource",
            description = "Get from every news source in one application",
            imageDrawable = R.drawable.ic_resource_news,
            titleColorRes = R.color.cornflower_blue,
            descriptionColorRes = R.color.black,
        ))

        setNextArrowColor(R.color.white)

    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        with(sharedPref.edit()) {
            putBoolean("isOnboardPassed", true)
            apply()
        }
        startActivity(Intent(this, NewsActivity::class.java))
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        with(sharedPref.edit()) {
            putBoolean("isOnboardPassed", true)
            apply()
        }
        startActivity(Intent(this, NewsActivity::class.java))
        finish()
    }
}