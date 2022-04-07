package com.example.myapplication1

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //변수 선언은 하는데, 초기화는 나중에 하겠다.
    lateinit var button1 : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
        //실질적으로 작성해야하는 코드
        //xml과 kt파일이 연결되는 코드
        setContentView(R.layout.activity_main)

        //가장 일반적인 변수 선언 방법
        val tv1 : TextView = findViewById(R.id.textView1)
        //추가
        // val tv2 = findViewById<TextView>(R.id.textView1)

        val rbar : RatingBar = findViewById(R.id.ratingBar)
        val btn : Button = findViewById(R.id.button)
        val chb : CheckBox = findViewById(R.id.checkBox)
        val rdo : RadioButton = findViewById(R.id.radioButton)

        tv1.visibility = View.INVISIBLE
        btn.visibility = View.INVISIBLE
        */

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.textView1.visibility = View.INVISIBLE
        //binding.button.visibility = View.INVISIBLE

        Log.d("myCheck", "안드로이드 시작 - 로그 출력")
    }
}