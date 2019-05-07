package com.kharisazhar.photomvvm

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.kharisazhar.photomvvm.utils.EspressoIdlingResource
import com.kharisazhar.photomvvm.view.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityTest {

    //added rule activity main
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Before
    fun setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingresource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingresource)
    }

    @Test
    fun testRecyclerView() {

        //check apps name is PhotoMVVM
        onView(withText("PhotoMVVM"))
            .check(matches(isDisplayed()))

        //search view with id = rv_list is diplay
        onView(withId(R.id.rv_list))
            .check(matches(isDisplayed()))

        //scroll position recyclerView to position 8
        onView(withId(R.id.rv_list))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(8))

        //Click item recyclerview position 8
        onView(withId(R.id.rv_list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(8, click()))
    }
}