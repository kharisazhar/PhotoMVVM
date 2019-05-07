package com.kharisazhar.photomvvm.utils

import android.support.test.espresso.IdlingResource
import android.support.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private val RESOURCE = "GLOBAL"
    private val countingIdlingResource = CountingIdlingResource(RESOURCE)

    val idlingresource: IdlingResource
        get() = countingIdlingResource

    fun increment() {
        countingIdlingResource.increment()
    }

    fun decrement() {
        countingIdlingResource.decrement()
    }
}