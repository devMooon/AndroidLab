package com.example.myapplication8

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

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

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("mobileApp", "Key Up")
        return super.onKeyUp(keyCode, event)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.d("mobileApp", "BACK Key Down")
    }
}