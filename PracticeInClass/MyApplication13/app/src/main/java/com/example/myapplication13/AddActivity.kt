package com.example.myapplication13

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.myapplication13.databinding.ActivityAddBinding
import com.example.myapplication13.databinding.ActivityMainBinding

class AddActivity : AppCompatActivity() {
    lateinit var binding : ActivityAddBinding
    //lateinit 나중에 초기화 하겠다.
    //값이 나중에 바뀌는 것이니 var!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//      setContentView(R.layout.activity_add)
        binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //getStringExtra 데이터 받기/ putExtra 데이터 보내기

        //mainActivity -> intent로 전달받는 데이터를 받기
        val d1 = intent.getStringExtra("data1")
        val d2 = intent.getStringExtra("data2")

        binding.tv.text = (d1+d2)

        //intent -> mainActivity로 데이터 전달
//        binding.button1.setOnClickListener {
//            intent.putExtra("test", "world")
//            setResult(RESULT_OK, intent)
//
//            //AddActivity를 종료한다.
//            finish()
//        }

        binding.button2.setOnClickListener {
            val intent = Intent()
            //intent에서 어떤 클래스 파일을 부르는게 아니라 메니페스트에 지정해놓은 거 호출 할거야!
            intent.action = "ACTION_EDIT"
            intent.data = Uri.parse("http://www.google.com")
            startActivity(intent)
        }
    }

    //button 대신에 옵션 버튼으로 구현

    //옵션 메뉴
    //Code -> Generate -> Override Method -> onCreateOptionsMenu
    //option menu를 menu_add.xml로 만들었는데, 그걸 옵션메뉴로 하겠다고 선언
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //option menu를 menu_add.xml로 만들었는데, 그걸 옵션메뉴로 하겠다고 선언
        menuInflater.inflate(R.menu.menu_add, menu)

        return super.onCreateOptionsMenu(menu)

    }

    //옵션 메뉴가 선택되었을 때 처리해주는 메소드
    //Code -> Generate -> Override Method -> onOptionsItemSelected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //선택된 아이디가 무엇인가
        if(item.itemId == R.id.menu_add_save){
            //maniactivity로 result라는 이름으로 binding.addEditView.text에 해당하는 단어를 전달함
            intent.putExtra("result", binding.addEditView.text.toString())
            setResult(RESULT_OK, intent)
            //AddActivity를 종료한다.
            finish()
            return true
        }
        return false
    }
}