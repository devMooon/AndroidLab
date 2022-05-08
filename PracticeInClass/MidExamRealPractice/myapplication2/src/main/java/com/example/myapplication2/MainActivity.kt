package com.example.myapplication2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication2.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
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

        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.viewpager.adapter = MyFragmentAdapter(this)

        binding.btn1.setOnClickListener {
            val intent = Intent(this, Fragment1::class.java)
            intent.apply {
                //this.putExtra("msg", text_main.text.toString())
            }
            startActivity(intent)
        }


    }
}
