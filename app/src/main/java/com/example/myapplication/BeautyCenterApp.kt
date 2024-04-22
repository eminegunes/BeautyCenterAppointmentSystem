package com.example.myapplication

import android.content.Context
import com.example.myapplication.di.CoreLibApplication
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class BeautyCenterApp : CoreLibApplication() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: BeautyCenterApp

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }
}