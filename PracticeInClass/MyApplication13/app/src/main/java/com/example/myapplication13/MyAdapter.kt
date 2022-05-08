package com.example.myapplication13

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication13.databinding.ItemRecyclerviewBinding

//리사이클러를 위해..
//1. MyAdapter.kt 필요
//2. MyViewHolder
//3. decoration도 가능. 필수 X

class MyViewHokder(val binding:ItemRecyclerviewBinding) : RecyclerView.ViewHolder(binding.root)

//리사이클러에 넣을 데이터를 매개변수로 받아야함
//변경이 가능하고, 할일 텍스트를 받는 리스트
class MyAdapter(val datas: MutableList<String>?) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
   //오버라이드 함수 3개 필요

    override fun getItemCount(): Int {
        //TODO("Not yet implemented")
        //리사이클러 뷰의 항목(datas) 개수
        return datas?.size ?:0
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            //메인 엑티비티가 아닌 곳에서 레이아웃에 접근하려면 메인 엑티비티(parent)에서 가져와야됨
    = MyViewHokder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        //TODO("Not yet implemented")
        //바인딩 가져오기
        val binding = (holder as MyViewHokder).binding
        //Todo Text 입력하기
        binding.itemTodo.text = datas!![position] //절대 NULL이면 안된다

    }



}