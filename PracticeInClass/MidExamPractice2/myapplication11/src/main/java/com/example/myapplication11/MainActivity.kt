package com.example.myapplication11

import android.app.Fragment
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication11.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator


//제트팩 라이브러리 1) appcompat - AppCompatActivity///////////////////////////////////////////
class MainActivity : AppCompatActivity() {

//////////////제트팩 라이브러리 4) viewPager2: 스와이프처럼 넘기는 화면 구성////////////////////////
    //fragment를 viewPager에 부착하기 위한 어댑터
    class MyFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
        //뷰페이저2에 들어갈 프레그먼트 확보
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





////////값 할당 미루기 : by lazy 변수를 본문에서 사용할 때 값을 할당 하겠다./////////////
    val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

//////제트팩 라이브러리 5) drawerlayout///////////////////////////////////
    lateinit var toogle : ActionBarDrawerToggle

///////////////////////////////////////////////////////////////////////////////////
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

/////////제트팩 라이브러리 1) appcompat: - ToolBar 등록/////////////////////////////////////
        setSupportActionBar(binding.toolbar)




////////////제트팩 라이브러리 5) drawerlayout 등록                  // String 에 등록!!
        toogle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_open, R.string.drawer_close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        //토글이 클릭됐을 때 이벤트
        toogle.syncState()
 //////////////제트팩 라이브러리 5) drawerlayout - 네비게이션에 있는 아이템이 선택되었을 때 동작//////////////////////////////////////////////////////
        binding.mainDrawerView.setNavigationItemSelectedListener {
            Log.d("mobileApp", "Navigation selected... ${it.title}")
            true
        }





/////////////fragment///////////////////////////////////////////////////////////////////////
        //제트팩 라이브러리 4) fragment 마지막 설정
        //fragmet 기본적으로 추가하기!!!
        //원하는 화면 하나만 추가됨!!!!!
//        val fragmentManager : FragmentManager = supportFragmentManager
//        val transaction : FragmentTransaction = fragmentManager.beginTransaction()
//        var fragment = Fragment1()
//        transaction.add(R.id.fragment_content, fragment) // fragment_content는 fragment1.xml에서 id 지정
//
//        //실행!!!!!!!! 해야 add가 실행됨
//        transaction.commit()


        //제트팩 라이브러리 3) viewPager2에 fragment 있는 어댑터 붙히기////////////////////////////////////////////////////////////////
        binding.viewpager.orientation = ViewPager2.ORIENTATION_HORIZONTAL //가로로 스와이프
        binding.viewpager.adapter = MyFragmentAdapter(this)



/////////머티리얼 라이브러리 3) Tab Layout: 탭바 만들고 뷰페이저를 연결 //뷰 페이저 위에서 스와이프 또는 탭 눌러서 화면 변경////////////////////////////////////
    TabLayoutMediator(binding.tab1, binding.viewpager) {
            tab, position -> tab.text = "TAB ${position + 1}" //position = 0, 1, 2
    }.attach()




//////////머티리얼 라이브러리 5) ExtendedFloationActionButton: 떠 있는 버튼//extend floating button 눌렀을 때 이벤트 리스너//////////////////////
        binding.fab.setOnClickListener {
            //확대 되면 글씨와 아이콘 한번에 나오는데.. 그거 확인~!
            when(binding.fab.isExtended){ //ㄹ버튼 크기가 확대되어 있으면
                true -> binding.fab.shrink() //버튼 크기를 줄이기
                false -> binding.fab.extend() //버튼 크기 확장
            }
        }
    }


///////제트팩 라이브러리 1) appcompat - 오른쪽 상단(점세개) 옵션메뉴 만들기///////////////////////////////////////////////////
// 방법 1. MainActivity.kt에서,
// 방법 2. menu_main.xml에서
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        //방법 1. 코드에서 메뉴 구성
        //Code -> override methods -> onCreateOptionsMenu
        //두번째 정수는 메뉴 아이템 구분 식별자, 마지막 문자열은 메뉴에 노출되는 문장
        //점세개 안에 들어가는 메뉴를 구성하는 것!

        //val menuItem1 : MenuItem? = menu?.add(0,0,0,"메뉴1")
        //val menuItem2 : MenuItem? = menu?.add(0,1,0,"메뉴2")

        //2. 리소스 파일에 menu 폴더를 만들고
        // menu 폴더에서 menu_main.xml 이라는 파일에서 메뉴 구성 후
        //바인딩으로 menu에 추가!
        menuInflater.inflate(R.menu.menu_main, menu)
    //////아래에 return문 주의!////////////////////////////////


    //////제트팩 라이브러리 1) appcompat - Search View 서치뷰! 메뉴 이벤트 처리///////////////////////////
        //menu_main.xml에 미리 item 추가하고 서치뷰로 임명 해줘야 함
        //서치뷰 import 확인하기!! //android.widget.SearchView라고 적혀있을텐데
        //androidx.appcompat.widget.SearchView 로 변경해야함!!!

        //서치뷰 아이템 가져오기
        val menuSearch = menu?.findItem(R.id.menu_search)
        val searchView = menuSearch?.actionView as SearchView

       ////서치뷰에서 사용자가 입력한 값을 가져오는 리스너
       searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            //사용자가 text를 입력했을 때
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

        //2. 리소스 파일에 menu 폴더를 만들고
        // menuInflater로 바인딩 한 걸 옵션메뉴로 만들어준다!!!
        return super.onCreateOptionsMenu(menu)
    }




///////////////옵션 선택 이벤트/////////////////////////////////////////////////////////////////////////////////////////////
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

///////////제트팩 라이브러리 5) drawerlayout
        //토글이 눌러졌는가
        if(toogle.onOptionsItemSelected(item)) return true


//////제트팩 라이브러리 1) appcompat - 오른쪽 상단 옵션메뉴에 이벤트 추가!!!//////////////////////////////////////////////////////////////////////
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