package com.example.myapplication.di

import android.app.Application
import android.content.Context

open class CoreLibApplication: Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: Application

        fun applicationContext(): Context {
            return instance.applicationContext
        }
    }

}