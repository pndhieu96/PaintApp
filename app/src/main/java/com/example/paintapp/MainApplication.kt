package com.example.paintapp

import android.app.Application
import android.graphics.Path

class MainApplication : Application() {

    companion object {
        var pathList = ArrayList<Path>()
        var colorList = ArrayList<Int>()
    }

    override fun onCreate() {
        super.onCreate()
    }
}