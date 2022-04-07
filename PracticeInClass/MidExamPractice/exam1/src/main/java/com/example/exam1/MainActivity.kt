package com.example.exam1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.exam1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener(

            binding.background.setBackgroundColor()
        )

    }
}