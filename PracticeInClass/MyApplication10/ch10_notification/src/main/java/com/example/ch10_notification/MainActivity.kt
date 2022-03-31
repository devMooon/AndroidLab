package com.example.ch10_notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.ch10_notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            //notification을 위해 manager와 builder 준비
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager //캐스트
            val builder : NotificationCompat.Builder

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // 버전이 26 이상인가
                //버전 26 이상에서는 notification channel 사용
                val ch_id = "one-channel"
                val channel = NotificationChannel(ch_id, "My Channel One", NotificationManager.IMPORTANCE_DEFAULT)
                channel.description = "My Channel One 소개"
                channel.setShowBadge(true) // 확인하지 않은 알림의 개수 표시
                channel.enableLights(true) // 불빛 설정
                channel.lightColor = Color.RED // 불빛 색 설정
                channel.enableVibration(true) // 진동 허용
                channel.vibrationPattern = longArrayOf(100, 200, 100, 200)// 진동 패턴 설정
                //(100, 200), (100, 200) -> 두개의 쌍으로 이루어짐.
                //(진동 x 시간, 진동 o 시간): msec 0.1초, 0.2 초

                val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION) //소리의 식별값 얻기
                val audio_attr = AudioAttributes.Builder() // 채널의 두번째 파라미터
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION) // 기계음 타입
                    .setUsage(AudioAttributes.USAGE_ALARM) // 어떤 용도로 사용할 것인지
                    .build()
                channel.setSound(uri, audio_attr)//소리에 대한 처리는 다 RingtonManager가 다 처리

                manager.createNotificationChannel(channel) //매니저에 등록

                //notification 객체 생성
                builder = NotificationCompat.Builder(this, ch_id)
            }
            else{
                //notification 객체 생성
                builder = NotificationCompat.Builder(this)
            }

            //notification 설정
            builder.setSmallIcon(R.drawable.small)
            builder.setWhen(System.currentTimeMillis())
            builder.setContentTitle("안녕하세요")
            builder.setContentText("모바일 앱 프로그래밍 시간입니다.")
            
            //큰 이미지는 BitmapFactory의 도움을 받아야함
            val bigPic = BitmapFactory.decodeResource(resources, R.drawable.big)
            val builderStyle = NotificationCompat.BigPictureStyle()
            builderStyle.bigPicture(bigPic)
            //builder 스타일 설정
            builder.setStyle(builderStyle)

            manager.notify(11, builder.build()) // 첫번째 인자: notification 순서 번로(임의의 값)

        }
    }
}