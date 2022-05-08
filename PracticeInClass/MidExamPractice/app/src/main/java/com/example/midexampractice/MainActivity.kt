package com.example.midexampractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
//Gradle은 app module 꺼만 보자!

//남은 여백을 다 채우고 싶다? 가로 채우려면.. width: 0dp, weight: 1

//myapplication1: 코틀린, View(디즈니)
//myapplication1_view: View(비밀번호 입력창)
//myapplication7: layout 연습
//myapplication7_layout: 전화번호 키패드
//myapplication8: 화면 이벤트
//myapplication8_event: chronometer
//myapplication9: 리소스(xml 버튼, 언어, 가로모드), 메신저 앱의 인트로 화면
//myapplication10: 퍼미션 설정, 버전 호환성,
//                  다이얼로그(
//                      토스트,
//                      데이트&타임 피커 다이얼로그,
//                      알림창 AlertDialog -> setItems(), setMultiChoiceItems(), setSingleChoiceItems()
//                          + setCancelable(false), setCanceledOnTouchOutside(false)
//                      커스텀 다이얼로그 AlertDialog -> XML파일에 다이얼로그 창 꾸미고, setView(dialogBinding.root)로 전달
//                          + LayoutInflater: 레이아웃 XML파일을 코드에서 초기화(전개, 생성)하는 기능
//                     )
//myapplication10_notificaion: 상태바 알람, 인텐트
//myapplication10_rington: 소리 알림, 음원 재생, 진동 알림




