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
    //lateinit 나중에 초기화 하겠다. //값이 나중에 바뀌는 것이니 var!!
    lateinit var binding : ActivityAddBinding

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

        //보내는 코드는 아래로 내려가면 옵션 메뉴 이벤트에 있음!!!!!!!!!!!!!!




        //intent -> mainActivity로 데이터 전달
//        binding.button1.setOnClickListener {
                //값 받아오기
//            intent.putExtra("test", "world")
                //이전 activity로 다시 되돌아가기
//            setResult(RESULT_OK, intent)
//
//             //AddActivity를 종료하기
//            finish()
//        }

//////////TwoActivity 호출///////////////////////////////////////////////////////////////////
        binding.button2.setOnClickListener {
            val intent = Intent()
            //TwoActivity를 호출한다고는 안 썼지만 TwoActivity가 암시적으로 호출됨
            //intent에서 어떤 클래스 파일을 부르는게 아니라 메니페스트에 지정해놓은 거 호출 할거야!
            //                묵시적 호출 예)     val intent = Intent(this, AddActivity::class.java)


            intent.action = "ACTION_EDIT" //암시적임
            intent.data = Uri.parse("http://www.google.com")
            startActivity(intent)
        }
    }


//////////////////////저장버튼!!!    button 대신에 옵션 버튼으로 구현/////////////////////////////////////////
///////옵션 메뉴 만들기/////////////////////////////////////////////////////////////////////////////////////
    //Code -> Generate -> Override Method -> onCreateOptionsMenu
    //option menu를 menu_add.xml로 만들었는데, 그걸 옵션메뉴로 하겠다고 선언
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //option menu를 menu_add.xml로 만들었는데, 그걸 옵션메뉴로 하겠다고 선언
        menuInflater.inflate(R.menu.menu_add, menu)

        return super.onCreateOptionsMenu(menu)
    }
//////옵션 메뉴가 선택되었을 때 처리해주는 메소드/////////////////////////////////////////////////////////////////////////////////
    //Code -> Generate -> Override Method -> onOptionsItemSelected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //저장이 선택 되었나? //됐으면 Activity 변환
        if (item.itemId == R.id.menu_add_save) {
            //getStringExtra 데이터 받기/ putExtra 데이터 보내기

            //maniactivity로 result라는 이름으로 binding.addEditView.text에 해당하는 단어를 전달함
            intent.putExtra("result", binding.addEditView.text.toString())
            //호출측(이전 activity)로 다시 되돌아가기 -> requestLauncher로 돌아감
            setResult(RESULT_OK, intent)
            //AddActivity를 종료한다.
            finish()
            return true
        }
        return false
    }
}