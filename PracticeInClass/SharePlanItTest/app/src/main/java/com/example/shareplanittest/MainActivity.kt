package com.example.shareplanittest

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.shareplanittest.databinding.ActivityMainBinding
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSubmit.setOnClickListener{
            val email : String = binding.userEmail.text.toString()
            val pw : String = binding.userEmail.text.toString()

            if(email.isEmpty()){
                Log.d("login", "아이디가 입력되지 않았습니다.")
            }
            else if(pw.isEmpty()){
                Log.d("login", "비밀버번호가 입력되지 않았습니다.")
            }
            else {
                Log.d("login", "아이디: ${binding.userEmail.text}, 비밀번호: ${binding.userPasswd.text}")

                val toast = Toast.makeText(this, "${binding.userEmail.text}님 환영합니다!", Toast.LENGTH_LONG)

                toast.show()

                //다른 activity로 전환
                //Intnet만들어서 AddActivity 호출할 것임을 표시
                val intent = Intent(this, AddActivity::class.java)
                //Intent에게 컴포넌트를 보낼 때 데이터도 함께 전송
                intent.putExtra("email", email)
                intent.putExtra("psw", pw)
                //Intent에게 시스템에 가서 요청하라고 함
                //startActivity(intent)
                //intent -> mainActivity 데이터 전달 호출
                startActivityForResult(intent, 10)
            }

        }
    }
}