package com.example.ch18_network

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MyApplication:Application() {
    //전역변수 선언
    companion object{
        var networkService:NetworkService
        val retrofit:Retrofit
            get() = Retrofit.Builder()
                .baseUrl("https://api.odcloud.kr/api/")
                    //json으로 converter
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        init {
            networkService = retrofit.create(NetworkService::class.java)
        }
    }

}