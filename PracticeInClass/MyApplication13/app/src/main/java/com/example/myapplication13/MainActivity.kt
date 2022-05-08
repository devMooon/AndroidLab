package com.example.myapplication13

import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.*
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication13.databinding.ActivityMainBinding
import java.io.BufferedReader
import java.io.File
import java.io.OutputStreamWriter

class MainActivity : AppCompatActivity() {
    var datas: MutableList<String>? = null
    lateinit var adapter: MyAdapter
    lateinit var sharedPreferences: SharedPreferences
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        val bgColor = sharedPreferences.getString("color", "")
        binding.rootLayout.setBackgroundColor(Color.parseColor(bgColor))

        //전체 화면 설정
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) { //>= 30
            window.setDecorFitsSystemWindows(false) //전체 화면으로 설정
            //설정을 위한 추가 코드
            val controller = window.insetsController
            if (controller != null) {
                controller.hide(WindowInsets.Type.statusBars() or WindowInsets.Type.navigationBars())
                //평소에는 투명하게 보이고 있다가 스와이프 하면 나타난다.
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else { //<=29
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )

        }
        //activityResultLauncher 액티비티에서 다양한 결과에 대한 사후 처리를 제공
        val requestLauncher: ActivityResultLauncher<Intent> =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                //호출된 콜백에 대한 내용
                //onActivityResult가 아닌 여기로 돌아옴
                val d3 = it.data!!.getStringExtra("result")?.let {
                    datas?.add(it)
                    //데이터 값이 바뀌었다고 리사이클러 뷰에 자동으로 적용되는 건 아님
                    //반드시 아래의 문장을 추가해줘야 datas에 변경된 값이 adapter에 반영이되어서 리사이클러 뷰가 변경됨
                    adapter.notifyDataSetChanged()
                }
                //Log.d("mobileApp", d3!!) //nonNull만 출력 가능해서, null이 아닐때만 출력 하겠다!!
            }
        //flation button을 클릭할때마다 AddActivity 하나씩 호출
        binding.fab.setOnClickListener {
            //Intnet만들어서 AddActivity 호출할 것임을 표시
            val intent = Intent(this, AddActivity::class.java)
            //Intent에게 컴포넌트를 보낼 때 데이터도 함께 전송
            intent.putExtra("data1", "mobile")
            intent.putExtra("data2", "app")
            //Intent에게 시스템에 가서 요청하라고 함
            //startActivity(intent)
            //intent -> mainActivity 데이터 전달 호출
            //startActivityForResult(intent, 10)
            requestLauncher.launch(intent)
        }

        //다시 실행될 때마다 데이터를 복원해줘야함
        //전달받은 객체에 대해서 명령을 실행한다: let
        //    datas = savedInstanceState?.let{
        //        it.getStringArrayList("mydatas")?.toMutableList()
        //    } ?: let{
        //        mutableListOf<String>()
        //    }

        datas = mutableListOf<String>()
        //DB읽어오기
        val db = DBHelper(this).readableDatabase
        val cursor = db.rawQuery("Select * from todo_tb", null)

        while (cursor.moveToNext()) {
            datas?.add(cursor.getString(1))
        }
        db.close()
        //리사이클러 뷰와 어댑터를 연결
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MyAdapter(datas)
        binding.mainRecyclerView.adapter = adapter
        binding.mainRecyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )

        val items = arrayOf<String>("내장")
        binding.fileBtn.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("저장 위치 선택")
                setIcon(android.R.drawable.ic_dialog_info)
                setSingleChoiceItems(items, 1, object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        if (p1 == 0) {//내장 메모리
                            //저장
                            val file = File(filesDir, "test.txt")
                            val writeStream: OutputStreamWriter = file.writer()
                            writeStream.write("hello android")
                            writeStream.write("$items[p1]")
                            for (i in datas!!.indices)
                                writeStream.write(datas!![i])
                            writeStream.flush()

                            //읽어 오기
                            val readStream: BufferedReader = file.reader().buffered()
                            readStream.forEachLine {
                                Log.d("mobileApp", "$it")
                            }
                        }
                    }
                })
                setPositiveButton("선택", null)
                show()
            }
        }
    }

    //액비티비 생명주기에서 비활성화 됐다가 다시 활성화 되면 데이터 다 날라감
    //따라서 Bundle에 저장해두기
//    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
//        super.onSaveInstanceState(outState, outPersistentState)
//        outState.putStringArrayList("mydatas", ArrayList(datas))
//    }

    //intent로부터 전송된 데이터를 받아오는 함수
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        //addActivity가 finish되고 return될 때
//        //이 함수로 돌아옴
//        //두개를 만족한 경우에만 내가 요청한 데이터
//        if(requestCode==10 && resultCode== RESULT_OK){
//            val d3 = data?.getStringExtra("test")
//            Log.d("mobileApp", d3!!) //nonNull만 출력 가능해서, null이 아닐때만 출력 하겠다!!
//        }
//    }


    override fun onResume() {
        super.onResume()
        val bgColor = sharedPreferences.getString("color", "")
        binding.rootLayout.setBackgroundColor(Color.parseColor(bgColor))
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_main_setting) {
            val intent = Intent(this, SettingActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

}

