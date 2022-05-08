package com.example.myapplication10_rington

import android.content.Context
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication10_rington.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //안드로이드 시스템은 알림(NOTIFICATION), 알람(ALARM), 벨소리(RINGTONE)등의 소리를 제공
        //이 소리는 RingtonManager로 얻을 수 있음!!!

        //소리 재생 RingtomManager
        binding.button1.setOnClickListener {
            //1. 소리의 식별값 얻기
            val notification : Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            //2. RingtoneManager.getRingtone()의 두번째 매개변수에 소리의 식별값을 전달하면
            //   소리를 재생하는 Rington 객체을 얻음
            val rington = RingtoneManager.getRingtone(applicationContext, notification)
            //3. play()를 호출하면 비로소 소리 재생!
            rington.play()
        }

        //음원 재생 MediaPlayer
        binding.button2.setOnClickListener {
            //1. 클래스에 리소스 정보(R.raw.funny_voices)를 지정                    //오류 주의: 음원 추가
            val player : MediaPlayer = MediaPlayer.create(this, R.raw.funny_voices)
            //2. start()를 호출하면 음원 재생
            player.start()
        }

        //진동 알림
        //1.오류 주의: manifests 파일에 <user-permissin>으로 vibrator permission 등록 해야 함
        //      <uses-permission android:name="android.permission.VIBRATE">
        binding.button3.setOnClickListener {
            //버전 호환성 고려해야함

///////////// Vibrator 객체 획득///////////////////////////////////////////////////
            val vibrator =
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){ //31버전 이상의 API인 경우
                    val vibratorManager = this.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                    vibratorManager.defaultVibrator
                }
                //31버전 미만의 API인 경우
                else {
                    getSystemService(VIBRATOR_SERVICE) as Vibrator
                }

///////////// Vibrator 를 이용한 진동 처리///////////////////////////////////////////////////
            //Logcat에 vibrator 검색 시 잘 되는지 나옴

            //26버전 이상의 API인 경우
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                vibrator.vibrate(
                    VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))

            }
            //26버전 미만의 API인 경우
            else {
                vibrator.vibrate(500)
            }
        }
    }
}