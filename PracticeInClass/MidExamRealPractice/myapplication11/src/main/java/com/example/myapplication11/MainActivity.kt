package com.example.myapplication11

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication11.databinding.ActivityMainBinding
import com.example.myapplication11.databinding.Fragment1Binding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
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

    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    lateinit var toogle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //제트팩 라이브러리 1) appcompat: - ToolBar 등록
        setSupportActionBar(binding.toolbar)

        //제트팩 라이브러리 5) drawerlayout 등록                  // String 에 등록!!
        toogle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_open, R.string.drawer_close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //토글이 클릭됐을 때 이벤트
        toogle.syncState()

/////////////fragment//////////////////////////////////////////////////////
        //제트팩 라이브러리 4) fragment 마지막 설정
        //fragmet 기본적으로 추가하기!!!
        //원하는 화면 하나만 추가됨!!!!!
//        val fragmentManager : FragmentManager = supportFragmentManager
//        val transaction : FragmentTransaction = fragmentManager.beginTransaction()
//        var fragment = Fragment1()
//        transaction.add(R.id.fragment_content, fragment) // fragment_content는 fragment1.xml에서 id 지정
//        실행!!!!!!!! 해야 add가 실행됨
//        transaction.commit()


/////////제트팩 라이브러리 3) viewPager2에 fragment 있는 어댑터 붙히기////////////////////////////////////////////////////////////////
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL //가로로 스와이프
        binding.viewpager.adapter = MyFragmentAdapter(this)


//////////제트팩 라이브러리 3) viewPager2 - 탭과 뷰페이저를 연결 //뷰 페이저 위에서 스와이프 또는 탭 눌러서 화면 변경////////////////////////////////////
//        TabLayoutMediator(binding.tab1, binding.viewpager) {
//                tab, position -> tab.text = "TAB ${position + 1}" //position = 0, 1, 2
//        }.attach()


        binding.mainDrawerView.setNavigationItemSelectedListener {
            Log.d("mobileApp", "Navigation selected... ${it.title}")
            true
        }
        //////////머티리얼 라이브러리 3) Tab Layout: 탭바 만들고 뷰페이저를 연결 //뷰 페이저 위에서 스와이프 또는 탭 눌러서 화면 변경////////////////////////////////////
        TabLayoutMediator(binding.tab1, binding.viewpager) {
                tab, position -> tab.text = "TAB ${position + 1}" //position = 0, 1, 2
        }.attach()

        //제트팩 라이브러리 5) drawerlayout - 네비게이션에 있는 아이템이 선택되었을 때 동작
        binding.mainDrawerView.setNavigationItemSelectedListener {
            Log.d("mobileApp", "Navigation selected... ${it.title}")
            true
        }

        binding.fab.setOnClickListener {
            //확대 되면 글씨와 아이콘 한번에 나오는데.. 그거 확인~!

//            when(binding.fab.isExtended){ //버튼 크기가 확대되어 있으면
//                true -> binding.fab.shrink() //버튼 크기를 줄이기
//                false -> binding.fab.extend() //버튼 크기 확장


            val intent = Intent(this, ExtendedActivity::class.java)

            //Intent에게 컴포넌트를 보낼 때 데이터도 함께 전송
            intent.putExtra("data1", "mobile")
            intent.putExtra("data2", "app")

            Log.d("mobile", "start")
            startActivity(intent)





//            }
        }

   }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)


        val menuSearch = menu?.findItem(R.id.menu_search)
        val searchView = menuSearch?.actionView as SearchView


        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(p0: String?): Boolean {
                //TODO("Not yet implemented")
                return true
            }

            //사용자가 검색 버튼을 눌렀을 때 = text가 submit 됐을 때
            override fun onQueryTextSubmit(p0: String?): Boolean {
                //TODO("Not yet implemented")
                //검색창의 내용 텍스트 뷰에 적용
                //binding.tv1.text = p0
                return true
            }
        })

        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL //가로로 스와이프
        binding.viewpager.adapter = MainActivity.MyFragmentAdapter(this)


        //2. 리소스 파일에 menu 폴더를 만들고
        // menuInflater로 바인딩 한 걸 옵션메뉴로 만들어준다!!!
        return super.onCreateOptionsMenu(menu)
    }

    //제트팩 라이브러리 1) appcompat - 오른쪽 상단 옵션메뉴에 이벤트 추가!!!
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        //제트팩 라이브러리 5) drawerlayout
        //토글이 눌러졌는가
        if(toogle.onOptionsItemSelected(item)) return true

        //1. 코틀린에서 메뉴를 만들었으면
        /*when(item.itemId){
            //menuItem을 만드는 함수에서 두번째 인자(id)로 구분
            0 -> {
                true
            }
            1 -> {
                true
            }
        }*/

        //2. xml에서 메뉴를 만들었으면
        when(item.itemId){
            //menuItem의 id로 구분
            R.id.menu1 -> {
                //binding 전역변수로 설정 해야됨
                //binding.tv1.setTextColor(Color.BLUE)
                true
            }
            R.id.menu2 -> {
                true
            }
        }

        return super.onOptionsItemSelected(item)
    }
}