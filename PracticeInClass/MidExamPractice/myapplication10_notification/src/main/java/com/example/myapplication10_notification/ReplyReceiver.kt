package com.example.myapplication10_notification

import android.app.NotificationManager
import android.app.RemoteInput
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class ReplyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        // TODO("ReplyReceiver.onReceive() is not implemented")

//////////////////원격 입력: 답장 기능 처리///////////////////////////////////////////////
        //RemoteInput을 이용하면 사용자의 입력을 받고 인텐트로 다른 컴포넌트에 전달할 수 있음

        //키 "key_text_replay"를 이용해 사용자가 입력한 remoteInput 가져오기
        val replyTxt = RemoteInput.getResultsFromIntent(intent)?.getCharSequence("key_text_replay")
        Log.d("mobileApp", "$replyTxt")


//////////////////알림 제거를 위한 코드/////////////////////////
        //activity에선 getSystemService()가능
        //브로드캐스트 리시버에서는 앞에 context를 써야함
        //알림(notification) 처리 매니저
        val manager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager //캐스트

        //클릭 시 알림(notification) 취소 //알림 호출측에 notifify 식별자를 써야함
        manager.cancel(11) //<- notifify 식별자
    }
}