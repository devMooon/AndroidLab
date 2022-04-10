package com.example.myapplication13

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication13.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var datas: MutableList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //리사이클러 뷰와 어댑터를 연결

        binding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.mainRecyclerView.adapter = MyAdapter(datas)


        //activityResultLauncher 액티비티에서 다양한 결과에 대한 사후 처리를 제공
        val requestLauncher: ActivityResultLauncher<Intent>
                = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            //호출된 콜백에 대한 내용
            //onActivityResult가 아닌 여기로 돌아옴
            val d3 = it.data!!.getStringExtra("result")?.let{
                datas.add(it)
            }
            Log.d("mobileApp", d3!!) //nonNull만 출력 가능해서, null이 아닐때만 출력 하겠다!!
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
            requestLauncher.launch((intent))
        }

        //다시 실행될 때마다 데이터를 복원해줘야함
        //전달받은 객체에 대해서 명령을 실행한다: let
        datas = savedInstanceState?.let{
            it.getStringArrayList("mydatas")?.toMutableList()
        } ?: let{
            mutableListOf<String>()
        }

    }

    //액비티비 생명주기에서 비활성화 됐다가 다시 활성화 되면 데이터 다 날라감
    //따라서 Bundle에 저장해두기
    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putStringArrayList("mydatas", ArrayList(datas))
    }

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


}
