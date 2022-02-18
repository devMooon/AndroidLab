package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //화면 출력 XML 명시
        setContentView(R.layout.activity_main)

        val binding : ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.visible.setOnClickListener{
            binding.image.visibility = View.VISIBLE
            //binding.image.setVisibility(View.VISIBLE)
        }
        binding.invisible.setOnClickListener{
            binding.image.visibility = View.INVISIBLE
        }
    }
}