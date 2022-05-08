package com.example.ch18_network

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ch18_network.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = RetrofitFragment()
        val bundle = Bundle() //json을 선택 했는지 xml을 선택했는지 fragment에 같이 전달
        binding.searchBtn.setOnClickListener{
            when(binding.rGroup.checkedRadioButtonId){
                R.id.json -> bundle.putString("returnType", "json")
                R.id.xml -> bundle.putString("returnType", "xml")
                else -> bundle.putString("returnType", "json")
            }
            fragment.arguments = bundle
            //fragment 보이기
            supportFragmentManager.beginTransaction()
                .replace(R.id.activity_content, fragment)
                .commit()
        }
    }
}