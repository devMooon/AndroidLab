package com.example.myapplication11


import android.app.Fragment
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.LinearLayout
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication11.databinding.Fragment1Binding
import com.example.myapplication11.databinding.ItemRecyclerviewBinding
import kotlin.time.measureTimedValue

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Fragment1.newInstance] factory method to
 * create an instance of this fragment.Tab
 */

//제트팩라이브러리 3) recyclerview 목록 화면 구성 제어를 위한 코드//////////////////////////////////////////
//필수!!! 뷰홀더, 어댑터, 레이아웃매니저
//선택! 아이템 데코레이션
//+ 항목을 구성하는 데이터에 새로운 데이터를 추가하거나 제거한 후 반드시 어댑터의 notifyDataSetChanged()함수 호출
//datas.add("new data")
//adapter.notifyDataSetChanged()



//뷰 홀더를 위한 클래스 만들기
//뷰 바인딩으로 recyclerview의 뷰홀더(목록 하나)를 다른 xml 파일로 지정해줌
class MyViewHolder(val binding : ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)


//어댑터 만들기
//datas -> 어댑터에서 다룰 데이터
//fragment의 onCreateView에서 목록을 만든 뒤 recyclerview Adapter에 전달
class MyAdapter(val datas:MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    //전달 받은 data의 크기 반환
    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return datas.size
    }
    //ViewHolder 만들기
    //MyAdapter에서 필요한 ViewHolder를 생성해서 RecyclerView에 있는 ViewHolder 타입으로 전달
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //TODO("Not yet implemented")
                                                            //layoutInflater는 MainActivity.kt에서만 제공됨!!!
        //                                                    따라서 LayoutInflater.from(parent.context) 호출로 MainActivity에 있는 inflater 가져옴
        return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    //데이터와 뷰 홀더를 연결시켜주는 작업을 함
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //TODO("Not yet implemented")

        //뷰 홀더에 있는 바인딩 뷰 객체 가져오기 <- itemRecyculerView
        val binding = (holder as MyViewHolder).binding
        //바인딩에 있는 뷰 객체 중에서 id를 가지고 있는 뷰에 값 할당
        //text에 데이터를 할당한다.
        binding.itemTv.text = datas[position]
    }
}


//추가~!!
//아이템 데코레이션///////////////////////////////////////////////////////////////////////////////////////
class MyDecoration(val context: Context) : RecyclerView.ItemDecoration() {

    //리사이클러 뷰가 호출되기 이전에 호출되는 함수
    //보통 항목에 대한 배경 이미지가 먼저 그려지고 항목이 그려짐
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        //ondraw는 리사이클러 뷰 항목이 그려지기 전에 호출되는 함수. 배경색 먼저 칠하는 느낌
        //c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.stadium), 0f, 0f, null)
    }

    //보통 항목이 먼저 출력되고 onDrawOver의 내용이 그려짐
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        //이미지가 정가운데에 오게 하기 위해서 값을 계산
        //스마트폰의 크기 / 2 - 이미지 크기 / 2
        val width = parent.width
        val height = parent.height

        val dr: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.kbo, null)
        val d_width = dr?.intrinsicWidth
        val d_height = dr?.intrinsicHeight

        val left = width / 2 - d_width?.div(2) as Int
        val top = height / 2 - d_height?.div(2) as Int

        //캔버스에 그림 그리기
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.kbo), left.toFloat(), top.toFloat(), null)
    }


    //항목 하나하나를 호출해 꾸며줌
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        //항목 하나가 가진 사각형
        //4개의 매개변수: 얼만큼 떨어진 곳에서 사각형을 그릴 것인가
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(10, 10, 10, 0)

        //각각의 아이템에 대한 배경색     //Color.gray
        view.setBackgroundColor(Color.parseColor("#49c1ff"))
        //3차원적으로 보이게 하기
        ViewCompat.setElevation(view, 20.0f)
    }
}




//////////////////////////////////////////////////////////////////////////////////////////////////
//제트팩 라이브러리 1) fragment
class Fragment1 : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }


//////반드시 작성!!///////////////////////////////////////////////////////////////////////////////
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

/////////////목록에 대한 정보를 만들고 MyAdapter로 전달하는 코드//////////////////////////////////////////////////////
    ///////다 왔다!!.........
        val datas = mutableListOf<String>() //목록이 변화될 수도 있어서 mutable로 설정
        //초기 데이터
        for (i in 1..9) { //(1~9)
            datas.add("item $i")
        }
        MyAdapter(datas)

///////////제트팩라이브러리 2) fragment 화면 구성
        //프래그먼트에 뷰 바인딩
        //1.xml의 뷰 객체를 가져올 수 있는 viewBinding///////////////////////////////////////////////////////////
                                                //MainActivity에서는 layoutInflater 라고 썼는데.. 여기는 달라!
                                                //위에서 전달받은 inflater를 넣어줌
        val binding = Fragment1Binding.inflate(inflater, container, false)



//////////제트팩라이브러리 3) recyclerview - 출력////////////////////////////////////////////////////////////////////
        //리니어 레이아웃을 사용하는 리사이클러 뷰
        val layoutManager = LinearLayoutManager(activity)

        //리니어 레이아웃 가로로 변경
        //layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        //그리드 레이아웃으로 변경
        //val layoutManager = GridLayoutManager(activity, 2)//열 개수
        binding.recyclerView.layoutManager = layoutManager
        //어댑터에 대한 설정
        binding.recyclerView.adapter = MyAdapter(datas)
        //데코레이션을 리사이클러 뷰에 반영
        binding.recyclerView.addItemDecoration(MyDecoration(activity as Context))
        return binding.root
    }


    /////////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////no /////////////////////////////////////
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Fragment1.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Fragment1().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

