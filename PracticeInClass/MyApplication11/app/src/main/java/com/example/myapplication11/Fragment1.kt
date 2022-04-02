package com.example.myapplication11

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
 * create an instance of this fragment.
 */
//리사이클러 제어를 위한 코드
//뷰 홀더, 어댑터, 레이아웃 메니저 선언 필요

//리사이클러 뷰의 뷰 홀더 만들기
//item.recyclerview.xml에 있는 걸 받아오기
//뷰 바인딩에 의해서 클래스로 변경됨 -> ItemRecyclerviewBinding
class MyViewHolder(val binding : ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

//리사이클러 뷰의 어댑터 만들기
class MyAdapter(val datas:MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        return datas.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        //TODO("Not yet implemented")
        return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //TODO("Not yet implemented")
        //데이터와 뷰 홀더를 연결시키는 작업

        //뷰 홀더에 있는 바인딩 뷰 객체 가져오기
        val binding = (holder as MyViewHolder).binding
        //바인딩에 있는 뷰 객체 중에서 id를 가지고 있는 뷰에 값 할당
        binding.itemTv.text = datas[position]
    }
}


//데코레이션을 위한 클래스
class MyDecoration(val context: Context) : RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)

        //ondraw는 리사이클러 뷰 항목이 그려지기 전에 호출되는 함수
        //따라서 배경색 먼저 칠하는 느낌
        //c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.stadium), 0f, 0f, null)

    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        //이미지가 가운데에 오게 하기
        //스마트폰의 크기 / 2 - 이미지 크기 / 2
        val width = parent.width
        val height = parent.height

        val dr: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.kbo, null)
        val d_width = dr?.intrinsicWidth
        val d_height = dr?.intrinsicHeight

        val left = width / 2 - d_width?.div(2) as Int
        val top = height / 2 - d_height?.div(2) as Int

        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.kbo), left.toFloat(), top.toFloat(), null)

    }

    //항목 하나하나를 호출해 꾸며줌
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.set(10, 10, 10, 0)
        view.setBackgroundColor(Color.parseColor("#49c1ff"))
        //3차원적으로 보이게 하기
        ViewCompat.setElevation(view, 20.0f)

    }
}
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

    //반드시 작성!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        //목록에 대한 정보를 만들고 MyAdapter로 전달하는 코드
        val datas = mutableListOf<String>()
        for (i in 1..9) {
            datas.add("item $i")
        }
        MyAdapter(datas)

        //프래그먼트 화면 구성
        //프래그먼트1.xml의 뷰 객체를 가져올 수 있는 viewBinding
        val binding = Fragment1Binding.inflate(inflater, container, false)

        //화면에 보여주기 전에 레이아웃에 대한 설정 필요 -> 리니어 레이아웃을 사용하는 리사이클러 뷰를 만들겠다.
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

