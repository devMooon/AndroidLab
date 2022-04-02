package com.example.ch10_notification

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
        //activity에선 getSystemService()가능
        //브로드캐스트 리시버에서는 앞에 context를 써야함
        val manager = context.getSystemService(AppCompatActivity.NOTIFICATION_SERVICE) as NotificationManager //캐스트

        //키 "key_text_replay"를 이용해 remoteInput 가져오기
        val replyTxt = RemoteInput.getResultsFromIntent(intent)?.getCharSequence("key_text_replay")
        Log.d("mobileApp", "$replyTxt")

        //클릭 시 알림 취소
        manager.cancel(11) //<- notifify 식별자
    }
}