package com.fairfaxmedia.newsapiclient


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.fairfaxmedia.newsapiclient.presentation.adapter.NewsAdapter
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @get: Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)



    /**
     *Test the context of application with application name test
     */
    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.fairfaxmedia.newsapiclient", appContext.packageName)
    }

    /**
     * Bottom navigation visibility test
     */

    @Test
    fun bvn_visibility_test() {
        onView(withId(R.id.bnv_news))
            .check(matches(isDisplayed()))
    }

    /**
     * Check the bottom navigation item displayed on initial screen
     */
    @Test
    fun bvn_item_visibility_test() {
        onView(withId(R.id.newsFragment))
            .check(matches(isDisplayed()))
        onView(withId(R.id.savedFragment))
            .check(matches(isDisplayed()))
    }

    /**
     *
     * test on saved news fragment , action click and navigation to saved news screen
     */
    @Test
    fun click_saved_news_test() {
        onView(withId(R.id.savedFragment)).perform(click())
        onView(withId(R.id.save_fragment)).check(matches(isDisplayed()))
    }

    /**
     * Test on click saved news
     * on back press should take the user back to news list view
     */

    @Test
    fun click_saved_news_and_back_press_test() {
        onView(withId(R.id.savedFragment)).perform(click())
        onView(withId(R.id.save_fragment)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.news_list_fragment)).check(matches(isDisplayed()))
    }

    /**
     * test the list item displayed within 2 seconds
     */
    @Test
    fun news_list_performance_test()
    {
        onView(withId(R.id.news_list_fragment)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.rvNews)).check(matches(isDisplayed()))

    }

    /**
     * Simulate user selection on list view item
     * check the selected item should navigate to webview
     */
    @Test
    fun news_list_click_test()
    {
        onView(withId(R.id.news_list_fragment)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.rvNews)).perform(RecyclerViewActions.actionOnItemAtPosition<NewsAdapter.NewsViewHolder>(0,click()))
        onView(withId(R.id.info_fragment)).check(matches(isDisplayed()))

    }

    /**
     * Save a news to saved news fragment test
     */
    @Test
    fun news_save_test()
    {
        onView(withId(R.id.news_list_fragment)).check(matches(isDisplayed()))
        Thread.sleep(2000)
        onView(withId(R.id.rvNews)).perform(RecyclerViewActions.actionOnItemAtPosition<NewsAdapter.NewsViewHolder>(0,click()))
        onView(withId(R.id.info_fragment)).check(matches(isDisplayed()))
        onView(withId(R.id.fab_save)).perform(click())

    }

    /**
     * Delete a saved news test
     */
    @Test
    fun news_saved_item_delete_test()
    {
        onView(withId(R.id.savedFragment)).check(matches(isDisplayed()))
        onView(withId(R.id.savedFragment)).perform(click())
        Thread.sleep(2000)
        onView(withId(R.id.rvSaved)).perform(RecyclerViewActions.actionOnItemAtPosition<NewsAdapter.NewsViewHolder>(0, swipeLeft()))

    }

    /**
     *
     *  check the search view is visible and clickable
     */
    @Test
    fun news_search_test()
    {
        Thread.sleep(2000)
        onView(withId(R.id.svNews)).check(matches(isDisplayed()))
        onView(withId(R.id.svNews)).perform(click())

    }
}