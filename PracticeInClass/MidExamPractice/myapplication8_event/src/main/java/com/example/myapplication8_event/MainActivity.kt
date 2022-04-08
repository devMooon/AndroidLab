package com.example.myapplication8_event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.KeyEvent
import android.widget.Toast
import com.example.myapplication8_event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //chronometer에 얼마만큼 카운트가 됐는가
    var pauseTime = 0L
    var initTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //star button click event
        binding.btnStart.setOnClickListener{
            //elapsedRealtime() 시스템이 부팅된 후의 시간 - 시간의 경과 파악
            Log.d("seoyeon", "elapsedRealtime: ${SystemClock.elapsedRealtime()}")
            //chronometer 스탑워치. 기준으로부터 얼마나 지났는지 base에 저장
            Log.d("seoyeon", "base: ${binding.chronometer.base}")
            Log.d("seoyeon", "pauseTime: ${pauseTime}")
            binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
            binding.chronometer.start()
            binding.btnStart.isEnabled=false
            binding.btnStop.isEnabled=true
            binding.btnReset.isEnabled=true
        }
        binding.btnStop.setOnClickListener{
            Log.d("seoyeon", "elapsedRealtime: ${SystemClock.elapsedRealtime()}")
            Log.d("seoyeon", "base: ${binding.chronometer.base}")
            Log.d("seoyeon", "pauseTime: ${pauseTime}")

            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.btnStart.isEnabled=true
            binding.btnStop.isEnabled=false
            binding.btnReset.isEnabled=true
        }
        binding.btnReset.setOnClickListener{
            pauseTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()
            binding.btnStart.isEnabled=true
            binding.btnStop.isEnabled=true
            binding.btnReset.isEnabled=false
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        //뒤로가기 버튼을 눌렀을 때
        if(keyCode == KeyEvent.KEYCODE_BACK){
            //두 번 연속 눌렸을 때 == 속도 차이가 얼마 안 날 때
            if(System.currentTimeMillis() - initTime > 3000) {// 현재 시간 - 저장된 시간
                //Toast
                Toast.makeText(this, "종료하려면 한번 더 누르세요.", Toast.LENGTH_LONG).show()
                initTime = System.currentTimeMillis()
                return true
            }
        }
        return super.onKeyDown(keyCode, event)
    }
}