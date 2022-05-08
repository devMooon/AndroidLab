package com.example.myapplication13

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
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

        binding.button5.setOnClickListener{
            //자신을 한번 더 호출
            val intent = Intent(this, AddActivity::class.java)
            startActivity(intent)
        }
        binding.button2.setOnClickListener {
            val intent = Intent()
            //intent에서 어떤 클래스 파일을 부르는게 아니라 메니페스트에 지정해놓은 거 호출 할거야!
            intent.action = "ACTION_EDIT"
            intent.data = Uri.parse("http://www.google.com")
            startActivity(intent)
        }

        //13-3 소프트 키보드 제어
        val manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        binding.button3.setOnClickListener{
            //강제로 포커스 주기
            binding.addEditView.requestFocus()
            //포커스가 있는 곳에서 키보드를 띄우겠다.
            manager.showSoftInput(binding.addEditView, InputMethodManager.SHOW_IMPLICIT)
        }
        binding.button4.setOnClickListener{
            manager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)

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
            //main으로 돌아가기 전에 데이터베이스에 값 저장
                val inputData = binding.addEditView.text.toString()
            //DB에 저장하기
            val db = DBHelper(this).writableDatabase
            db.execSQL("insert into todo_tb(todo) values (?)", arrayOf<String>(inputData))
            db.close()


            //maniactivity로 result라는 이름으로 binding.addEditView.text에 해당하는 단어를 전달함
            intent.putExtra("result", inputData)
            setResult(RESULT_OK, intent)
            //AddActivity를 종료한다.
            finish()
            return true
        }
        return false
    }
}