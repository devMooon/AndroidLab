package com.example.mid20200675

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.mid20200675.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    //제트팩 라이브러리 4) viewPager2: 스와이프처럼 넘기는 화면 구성
    //fragment를 viewPager에 부착하기 위한 어댑터
    class MyFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        //뷰레이저2에 들어갈 프레그먼트 확보
        // 프래그먼트 묶음
        val fragments:List<Fragment>
        init {          //클래스 이름 3개
            fragments = listOf(Fragment1(), Fragment2(), Fragment3())
        }
        //크기 반환
        override fun getItemCount(): Int {
            //TODO("Not yet implemented")
            return fragments.size
        }
        //position번째 프래그먼트를 반환
        override fun createFragment(position: Int): Fragment {
            //TODO("Not yet implemented")
            return fragments[position]
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL //가로로 스와이프
        binding.viewpager.adapter = MyFragmentAdapter(this)

        //////////머티리얼 라이브러리 3) Tab Layout: 탭바 만들고 뷰페이저를 연결 //뷰 페이저 위에서 스와이프 또는 탭 눌러서 화면 변경////////////////////////////////////
        TabLayoutMediator(binding.tab1, binding.viewpager) {
                tab, position -> tab.text = "TAB ${position + 1}" //position = 0, 1, 2
        }.attach()

    }
}