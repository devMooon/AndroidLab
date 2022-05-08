package com.example.shareplanittest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shareplanittest.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra("email")
        val pw = intent.getStringExtra("pw")

        binding.tv.text = (email + "님 환영합니다.")
    }
}