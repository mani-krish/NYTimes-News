package com.nytimes.news

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.rule.ActivityTestRule
import com.nytimes.news.presentation.view.BaseActivity
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class NewsListFragmentInstrumentationTest {

    @get:Rule
    var mActivityRule = ActivityTestRule(BaseActivity::class.java)

    @Before
    fun launchFragmentUI() {
        mActivityRule.activity
            .fragmentManager.beginTransaction()
    }

    @Test
    fun testRecycleViewVisibility() {
        onView(withId(R.id.recyclerViewPopularNews)).check(matches(isDisplayed()))
    }

    @Test
    fun testRecycleViewScroll() {
        val recyclerView =
            mActivityRule.activity.findViewById<RecyclerView>(R.id.recyclerViewPopularNews)
        val itemCount = recyclerView.adapter!!.itemCount
        onView(withId(R.id.recyclerViewPopularNews))
            .inRoot(RootMatchers.withDecorView(Matchers.`is`(mActivityRule.activity.window.decorView)))
            .perform(scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))
    }

    @Test
    fun testToolBarVisibility() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }

    @Test
    fun testProgressBarVisibility() {
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }

}
