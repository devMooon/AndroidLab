package com.example.myapplication13

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

//SQLiteOpenHelper로 데이터베이스 만들기
class DBHelper(context: Context) : SQLiteOpenHelper(context, "testdb", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        //p0?.execSQL("create table todo_tb(_id integer primary key autoincrement, todo not null)")
        p0?.execSQL("create table todo_tb(" +
                "_id integer primary key autoincrement, " +
                "todo not null)")

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        //TODO("Not yet implemented")
    }

}