package com.example.myapplication10_notification

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import com.example.myapplication10_notification.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //notification(알람): 상태바에 앱의 정보를 출력하는 것
        //원래 상태바는 시스템이 관리하는 것이라 앱이 직접 제어할 수는 없지만,
        //앱에서 시스템에 의뢰하면 시스템에서 관리하는 생태 바에 앱의 알림을 출력할 수 있다.
        //따라서 앱의 화면을 구성하거나 사용자 이벤트를 처리하는 프로그래밍과는 구조가 다르고
        //알림을 위해 제공하는 API를 이용해야함

        //알림음 NotificationManager의 notify()함수로 발생
        //-> notify()함수에는 ficationCompat.Builder가 만들어주는 Notification 객체를 대입하고 이 객체에는 알림 정보가 저장된다.
        //-> NotificationCompat.Builder를 만들 때 NotificatinChannel정보를 대입해야함

        //결론
        //1. NotificationChannel로 알림 채널 만들기
        //2. 채널에 다양한 정보 설정
        //3. 채널을 NotificationManager 에 등록
        //4. 채널을 이용해 NotificationCompat.Builder 생성

        //빌더를 만들었으면 이 빌더를 이용해 Notification 객체를 만들어야 함
        //이 객체에 출력할 이미지, 문자열, 스몰아이콘, 제목, 알림시각, 내용 등의 정보를 담아야 됨

        //5. Builder로 Notification 객체 만들고 설정
        //6. 이 Notification 객체를 NotificationManager의 notify() 함수에 대입

        binding.button1.setOnClickListener {
            //notification을 위해 manager와 builder 준비
            val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager //캐스트
            val builder : NotificationCompat.Builder

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) { // 버전이 26 이상인가
                //버전 26 이상에서는 notification channel 사용
                val ch_id = "one-channel"

                //1. NotificationChannel로 알림 채널 만들기
                val channel = NotificationChannel(ch_id, "My Channel One", NotificationManager.IMPORTANCE_DEFAULT)

                //2. 채널에 다양한 정보 설정

                //* 채널의 설명 문자열
                channel.description = "My Channel One 소개"
                //* 홈 화면의 아이콘에 배지 아이콘 출력 여부 // 확인하지 않은 알림의 개수 표시
                channel.setShowBadge(true)
                //* 불빛 표시 여부
                channel.enableLights(true)
                //* 불빛이 표시 된다면 불빛의 색상 설정
                channel.lightColor = Color.RED
                //* 진동을 울릴지 여부
                channel.enableVibration(true)
                //* 진동을 울린다면 진동의 패턴
                channel.vibrationPattern = longArrayOf(100, 200, 100, 200)
                //(100, 200), (100, 200) -> 두개의 쌍으로 이루어짐.
                //(진동 x 시간, 진동 o 시간): msec 0.1초, 0.2 초

                //소리 재생 RingtomMager
                //소리의 식별값 얻기
                val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
                val audio_attr = AudioAttributes.Builder() // 채널의 두번째 파라미터
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION) // 기계음 타입
                    .setUsage(AudioAttributes.USAGE_ALARM) // 어떤 용도로 사용할 것인지
                    .build()

                //* 알림음 설정
                channel.setSound(uri, audio_attr)//소리에 대한 처리는 다 RingtonManager가 다 처리

                //3. 채널을 NotificationManager 에 등록
                manager.createNotificationChannel(channel)

                //4. 채널을 이용해 builder 생성
                builder = NotificationCompat.Builder(this, ch_id)
            }
            else{
                //channel 개념 X
                //notification 객체 생성
                builder = NotificationCompat.Builder(this)
            }

            //5. Builder로 Notification 객체 만들고 설정
            //스몰 아이콘
            builder.setSmallIcon(R.drawable.small)
            //발생 시각
            builder.setWhen(System.currentTimeMillis())
            //제목
            builder.setContentTitle("안녕하세요")
            //내용
            builder.setContentText("모바일 앱 프로그래밍 시간입니다.")




            //큰 이미지는 BitmapFactory의 도움을 받아야함
            //BitmapFactory 객체의 bigPicture 프로퍼티에 출력할 이미지를 비트맵 형식으로 지정하고,
            //이렇게 만든 BigPicture 객체를 빌더의 setStyle()함수에 지정

            val bigPic = BitmapFactory.decodeResource(resources, R.drawable.big)
            val builderStyle = NotificationCompat.BigPictureStyle()
            builderStyle.bigPicture(bigPic)
            //builder 스타일 설정
            builder.setStyle(builderStyle)




            //알림 터치 이벤트
            //알림이 터치 됐을 때 실행되어야 하는 정보를 Notification 객체에 담아두고,
            //실제 이벤트가 발생하면 Notification 객체에 등록된 이벤트 처리 내용을 시스템이 처리
            //사용자가 알림을 터치하면 앱의 액티비티 또는 브로드캐스트리시버를 실행해야 하는데,
            //이를 실행하려면 ***인텐트***를 이용해야한다. (앱의 컴포넌트를 실행하는 데 필요한 정보)

            //1. 인텐트 준비
            //2. Notification 객체에 담아서 이벤트가 발생할 때 인텐트를 실행해달라고 시스템에 의뢰(-> PendingIntent 클래스 이용)

            val replyIntent = Intent(this, ReplyReceiver::class.java)//호출할 파일
            //인텐트를 브로드케스트로 가지고 올 수 있도록 등록 해야함
            //터치 이벤트
            val replyPendingIntent = PendingIntent.getBroadcast(this, 30, replyIntent,PendingIntent.FLAG_MUTABLE)
            //똑같은 알림이 발생했을 시 처리방법 //FLAG_IMMUTABLE(변경 없음) //FLAG_MUTABLE(변경 있음)
            //터치 이벤트 빌더에 등록
            builder.setContentIntent(replyPendingIntent)

            //액션등록: 이벤트 처리가 목적이므로 인텐트 등록
            builder.addAction(
                NotificationCompat.Action.Builder(
                    //인자 3개. 아이콘, 제목, 인텐트
                    android.R.drawable.stat_notify_more,
                    "Action",
                    replyPendingIntent
                ).build()
            )

            //원격 입력
            //원격 입력도 액션의 한 종류, RemoteInput에 사용자 입력을 받는 정보를 설정한 후 액션에 추가
            val remoteInput = RemoteInput.Builder("key_text_replay").run{
                setLabel("답장")
                build()
            }

            //원격 입력도 액션이므로 이벤트 처리를 위해 인텐트 등록
            builder.addAction(
                NotificationCompat.Action.Builder(
                    //인자 3개. 아이콘, 제목, 인텐트
                    R.drawable.send,
                    "답장",
                    replyPendingIntent
                ).addRemoteInput(remoteInput).build()
            )




            //6. 이 Notification 객체를 NotificationManager의 notify() 함수에 대입
            //notification 설정
            manager.notify(11, builder.build())
            // 첫번째 인자: 알림을 식별하는데 사용하는 숫자. 개발자가 임의로 지정
            // 사용자가 폰에 발생한 알림을 코드에서 취소할 때 사용됨 ex)manager.cancle(11)

            //알림 취소 막기
            //builder.setAutoCancel(false) //알림을 터치해도 사라지지 않음
            //builder.setOngoing(true) //알림을 스와이프해도 사라지지 않음
            //만약 두가지를 모두 설정했다면 사용자가 알람을 삭제할 수 없으며, 개발자가 코드에서 cancle 해줘야함

        }
    }
}