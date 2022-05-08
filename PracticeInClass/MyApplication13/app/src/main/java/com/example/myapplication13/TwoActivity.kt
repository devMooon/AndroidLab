package com.example.myapplication13

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication13.databinding.ActivityTwoBinding
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consume
import kotlinx.coroutines.channels.consumeEach
import kotlin.system.measureTimeMillis

class TwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_two)

        //코루틴 사용을 위해 오래 걸리는 작업 추가
        val binding = ActivityTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button6.setOnClickListener {
//            var sum = 0L
//            var time = measureTimeMillis {
//                for(i in 1..4000000000){
//                    sum += i
//                }
//            }
//            Log.d("mobileApp", "걸린 시간 : ${time}")
//            binding.tv3.text = "합계 : ${sum}"

            //코루틴으로 코드 작성
            val channel = Channel<Long>()
            val bgScope = CoroutineScope(Dispatchers.Default + Job())
            bgScope.launch {
                var sum = 0L
                var time = measureTimeMillis {
                    Log.d("mobileApp", "시작")
                    for(i in 1..400){ //30분이 지나도 for문이 끝이 안나 숫자를 조정 했습니다
                        Log.d("mobileApp", "실행중...")

                        sum += i
                    }
                    Log.d("mobileApp", "끝")
                }
                Log.d("mobileApp", "걸린 시간 : ${time}")
                channel.send(sum)
            }
            val mainScope = GlobalScope.launch(Dispatchers.Main) {
                channel.consumeEach {
                    binding.tv3.text = "합계 : ${it}"
                }
            }
        }
    }
}