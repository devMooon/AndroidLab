package com.example.myapplication13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication13.databinding.ActivityMainBinding




class MainActivity : AppCompatActivity() {

////Recyclerivew에 들어가는 데이터//////////////////////////////////////////////////
    var datas: MutableList<String>? = null
    lateinit var adapter : MyAdapter


////////////////////////////////////////////////////////////////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



/////////인텐트: 다시 실행될 때마다 데이터를 복원해줘야함///////////////////////////////////////////////////////////////////
        //전달받은 객체에 대해서 명령을 실행한다: let 문법
        datas = savedInstanceState?.let {
            it.getStringArrayList("mydatas")?.toMutableList()
                                    //save 했을 때 키값
        } ?: let{
            //null인 경우
            mutableListOf<String>()
        }






//////////Recyclerivew와 MyAdapter.kt(어댑터, 뷰홀더)를 연결///////////////////////////////////////////////////////////////
        //리사이클러 뷰와 어댑터를 연결
        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)

        //MyAdapter에는 Recyclerivew 항목에 대한 데이터가 들어가야 함
        //datas 변수 상단에서 만들기!!
        adapter = MyAdapter(datas)
        binding.mainRecyclerView.adapter = adapter


////////Recyclerivew에 데코레이션 - 구분선 추가///////////////////////////////////////////////////////////////////
        binding.mainRecyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )






////////ActivityResultLauncher 액티비티에서 다양한 결과에 대한 사후 처리를 제공/////////////////////////////////////////////////////
        val requestLauncher: ActivityResultLauncher<Intent>
                = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            ////ActivityResultLauncher
            //호출된 콜백에 대한 내용
            //호출된 측에서 다시 이 Activity로 돌아올 때 //onActivityResult 대신 여기로 돌아옴
            val d3 = it.data!!.getStringExtra("result")?.let{
                //getStringExtra를 통해 전달 받은 객체: it
                datas?.add(it)
                //데이터 값이 바뀌었다고 리사이클러 뷰에 자동으로 적용되는 건 아님
                //반드시 아래의 문장을 추가해줘야 datas에 변경된 값이 adapter에 반영이되어서 리사이클러 뷰가 변경됨
                adapter.notifyDataSetChanged()
            }
            //Log.d("mobileApp", d3!!) //nonNull만 출력 가능해서, null이 아닐때만 출력 하겠다!!
        }






//////////floating button을 클릭할때마다 AddActivity 호출/////////////////////////////////////////////////
        binding.fab.setOnClickListener {
            //Intnet만들어서 AddActivity 호출할 것임을 표시
            val intent = Intent(this, AddActivity::class.java)

            //Intent에게 컴포넌트를 보낼 때 데이터도 함께 전송
            intent.putExtra("data1", "mobile")
            intent.putExtra("data2", "app")

            //Intent에게 시스템에 가서 요청하라고 함 -> Activity 화면 바뀜 성공
            //startActivity(intent)

            //intent -> mainActivity 데이터 전달 호출
            //startActivityForResult(intent, 10)

            //ActivityResultLauncher로 intent 호출
            requestLauncher.launch((intent))
        }
    }



///////액비티비 생명주기에서 비활성화 됐다가 다시 활성화 되면 데이터 다 날라감//////////////////////////////////////////////////////////////////////////
    //따라서 Bundle에 저장해두기
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putStringArrayList("mydatas", ArrayList(datas))
    }



//////////////////intent로부터 전송된 데이터를 받아오는 함수/////////////////////////////////////////////////////////////////////////
    // Code -> Override Method -> onActivityResult

    //AddActivity에서 setResult()했을 때 이 함수로 되돌아옴
//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        //코드와 요청값 두개를 만족한 경우에만 내가 요청한 데이터
//        if(requestCode==10 && resultCode== RESULT_OK){
//            val d3 = data?.getStringExtra("test")
//            Log.d("mobileApp", d3!!) //!!: Log에는 nonNull만 출력 가능해서, null이 아닐때만 출력 하겠다!!
//        }
//    }


}
