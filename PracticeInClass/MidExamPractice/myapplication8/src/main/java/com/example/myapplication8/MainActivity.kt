package com.example.myapplication8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //화면 클릭 이벤트
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action){ //event가 NULL일 수도 있어서 ? 붙임
            MotionEvent.ACTION_DOWN -> {
                Log.d("mobileApp", "ACTION_DOWN : ${event.x}, ${event.y}")
            }
            MotionEvent.ACTION_UP -> {
                Log.d("mobileApp", "ACTION_UP : ${event.rawX}, ${event.rawY}")
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d("mobileApp", "ACTION_MOVE")
            }
        }
        return super.onTouchEvent(event)
    }


    //키보드를 눌렀을 때
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("mobileApp", "Key Down")
        when(keyCode){
            KeyEvent.KEYCODE_0 -> {Log.d("mobileApp", "0 Key Down")}
            KeyEvent.KEYCODE_B -> {Log.d("mobileApp", "B Key Down")}
            //KeyEvent.KEYCODE_BACK -> {Log.d("mobileApp", "BACK Key Down")}
            KeyEvent.KEYCODE_VOLUME_UP -> {Log.d("mobileApp", "VOLUME_UP")}
            KeyEvent.KEYCODE_VOLUME_DOWN -> {Log.d("mobileApp", "VOLUME_DOWN")}
        }
        return super.onKeyDown(keyCode, event)
    }

    //키보드에서 손을 땠을 때
    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("mobileApp", "Key Up")
        return super.onKeyUp(keyCode, event)
    }

    //하단의 뒤로가기 버튼을 눌렀을 때
    //Toast처리 가능
    override fun onBackPressed() {
        super.onBackPressed()
        Toast.makeText(this, "종료하려면 한번 더 누르세요.", Toast.LENGTH_LONG).show()
        Log.d("mobileApp", "BACK Key Down")
    }
}