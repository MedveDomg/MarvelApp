package com.medvedomg.marvelapp

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.medvedomg.marvelapp.presentation.details.DetailsFragment
import org.hamcrest.Matchers.containsString
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class TitleTest {

    @Test
    fun testEventFragment() {
        launchFragmentInContainer<DetailsFragment>()
        onView(withId(R.id.tvScreenName))
            .check(matches(withText(containsString("Comics details"))))
    }
}