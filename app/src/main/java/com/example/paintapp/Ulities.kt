package com.example.paintapp

import android.content.Context
import android.graphics.Color
import androidx.core.content.ContextCompat

class Ulities {

    companion object {
        fun getColor(context: Context, colorResId: Int): Int {
            return ContextCompat.getColor(context, colorResId)
        }
    }
}