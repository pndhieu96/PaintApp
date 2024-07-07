package com.example.paintapp

import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import com.example.paintapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(savedInstanceState != null) {
            binding.paintView.setInitValue(MainApplication.pathList, MainApplication.colorList)
        }

        binding.btnRed.setOnClickListener {
            binding.paintView.currentColor = Ulities.getColor(this, R.color.red)
        }
        binding.btnPurple.setOnClickListener {
            binding.paintView.currentColor = Ulities.getColor(this, R.color.dark_purple)
        }
        binding.btnBlack.setOnClickListener {
            binding.paintView.currentColor = Ulities.getColor(this, R.color.black)
        }
        binding.btnEraser.setOnClickListener {
            binding.paintView.reset()
        }
    }
}