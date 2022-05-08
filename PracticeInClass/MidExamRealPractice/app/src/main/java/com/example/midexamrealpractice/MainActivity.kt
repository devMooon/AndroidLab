package com.example.midexamrealpractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.midexamrealpractice.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    //뷰 페이져를 위한 어댑터
    class MyFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        //프래그먼트 묶음
        val fragments:List<Fragment>
        init {
            fragments = listOf(Fragment1(), Fragment2(), Fragment3())
        }
        override fun getItemCount(): Int {
            //TODO("Not yet implemented")
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            //TODO("Not yet implemented")
            return fragments[position]
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //제트팩 라이브러리 3) viewPager2에 어댑터 붙히기
        binding.viewPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewPager.adapter = MyFragmentAdapter(this)

        //제트팩 라이브러리 3) viewPager2 - 탭과 뷰페이저를 연결
//        TabLayoutMediator(binding.tab1, binding.viewPager) {
//                tab, position -> tab.text = "TAB ${position + 1}" //position = 0, 1, 2
//        }.attach()


    }
}