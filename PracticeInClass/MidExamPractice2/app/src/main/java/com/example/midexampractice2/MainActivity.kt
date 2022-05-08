package com.example.midexampractice2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}

//Gradle은 app module 꺼만 보자!

//남은 여백을 다 채우고 싶다? 가로 채우려면.. width: 0dp, weight: 1

//myapplication11: 1. 제트팩 라이브러리
//축구장                1) appcompat: 앱 API 레벨 호환성을 해결
//                     원래 기본적으로 추가되어 있음!!!!

//                     [ActionBar OR ToolBar]
//                     ActionBar는 이미 엑티비티의 구성요소이고 ToolBar는 새로 추가하는 요소이다.
//                     ToolBar는 그만큼 개발자가 작성해야할 코드가 많아 복잡하지만 그만큼 개발자 마음대로 꾸미고 제어할 수 있다.

//                          - ActionBar
//                              1) 옵션메뉴: 수정 하고 싶으면 -> (thems.xml)
//                              2) 액션뷰(서치뷰): menu_main에 item 추가하고 서치뷰로 임명
//                                              MainActivity.kt에서 서치뷰 이벤트 처리
//                          - ToolBar
//                              1. thems에서 ActionBar 출력되지않게!
//                              2. activity_main.xml에 툴바 등록
//                              3. Main_Activity에 액션바 내용을 툴바에 적용

//                  2) fragment: 액티비티처럼 동작하는 뷰(탭버튼: 카톡 친구목록, 채팅방, 더보기...)
//                     >> 그래들!!!!!!
//                               1. fargment kt, xml파일 만들기
//                               2. activity_main.xml파일에서 fragment가 들어갈 공간 만들기
//                               3. MainActivity 에서 가지고 오고 싶은 fragment 설정

//                              ViewPager와 연결해 스크롤
//
//                             작업을 하고싶다면?
//                               3. fargment.kt에서 onCreateView()함수 안에 binding으로 객체 생성하기
//                               4. MainActivity.kt에서 ViewPager와 어댑터로 연결
//                               5. activity_main.xml에서 ViewPager2 자리 만들기

//                  3) recyclerview: 목록 화면 구성
//                     >> 그래들!!!!!!
//                      ViewHolder(필수): 항목에 필요한 뷰 객체를 가짐
//                      Adapter(필수): ViewHolder에 있는 뷰 객체에 적절한 데이터를 대입해 항목을 구성
//                      LayoutManager(필수): Adapter가 만든 항목을 어떻게 배치할지 결정
//                      ItemDecoration(옵션): 항목을 꾸밈

//                                1. fargment1 kt, xml파일 만들기
//                                2. fragment1.xml에 recyclerview 등록
//                                3. recyclerview의 목록 "하나"를 꾸밀 xml파일을 하나 더 만듬(item_recyclerview.xml)에 디자인 등록
//                                4. fragment1.kt에서 recyclerview 제어
//                                          !!뷰홀더, 어댑터, 레이아웃매니저, 아이템 데커레이션!!
//
//                  4) viewPager2: 스와이프처럼 넘기는 화면 구성
//                      >> 그래들!!!!!!
//                         Adapter 사용
//                                1. activity_main.xml에 viewPager2 등록
//                                2. Main_Activity에서 Adapter 이용

//                  5) drawerlayout: 옆에서 서랍처럼 열리는 화면
//                      >> 그래들!!!!!!
//                      (activity_main.xml)
//                      1. <보여지는 태그>화면에 보여지는 내용을 LinearLayout 하나로 묶어줌
//                      2. <숨겨진 태그> 어느 쪽에서 나올건지 선택 가능. gravity -> start

//                      3. Main_Activity.kt에서 toogle 설정
//                         Adapter 사용
//                                1. activity_main.xml에 drawerlayout 등록
//                                2. Main_Activity에서 toogle 설정
//

//뷰에 높낮를 줘 예쁘게 꾸미는 디자인 라이브러리
//myapplication11: 2. 머터리얼 라이브러리
//      >> 그래들!!!!!!
//                  1) AppBar: 화면 위쪽 영역 꾸미기
//                      AppBar에 ToolBar추가 가능
//                          - activity_main.xml에 앱바에 툴바 포함한채 구현


//                  2) CoordinatorLayout: 뷰끼리 상호작용하기(스크롤)
//                          + CollapsingToolbarLayout
//                               1. 스크롤 연동하기
//                                   1) activity_main.xml에서 Appbar와 Appbar 아래의 Toolbar, Image가 있는데,
//                                      그 전체를 감싸는 태그를(아마 LinearLayout) CoodinateLayout으로 변경하기!!
//                                   2) Appbar의 내용물인 Toolbar와 Image를  CollapsingToolbarLayout으로 감싸줌
//                                        -> 스크롤 했을 때 앱바가 접히게 함
//                                   3) CollapsingToolbarLayout과 Appbar의 내용물인 Toolbar와 Image, viewPager2에 적절한 속성으로 스크롤 설정
//
//                               2. 중첩 스크롤 뷰 사용하기 - 속성
//                               3. 컬랩싱 툴바 레이아웃 (CollapsingToolbarLayout)

//                  3) Tab Layout: 탭바 만들기
//                      1. fragment2.kt -> fragment + TabLayout
//                          - fregment2.xml에 [탭]TabLayout(+TabItem), [탭 눌렀을 때 바뀌는 화면]FrameLayout 추가!
//                          - 탭 바 눌렀을 때 나타나는 fragment21, 22, 23 만들기
//                          - fregmant2.kt에 어떤 탭 버튼을 눌렀을 때 어떤 화면이 나올지 OnCreateView 정의

//                      2. Main_Activity.kt -> activity + TabLayout
//                          - activity_main.xml에 [탭]TabLayout 추가!
//                          - Main_Activity.kt에 탭바 만들고 뷰페이저를 연결
//
//
//                  4) navigationView - drawerlayout의 화면 구성
//                       1. activity_main.xml에 NavigationView 추가(headerLayout, menu 속성 추가하기)
//                                          navigation_header.xml, navigation_manu.xml
//                       2. 주의! 꼭 drawerlayout의 숨긴 부분에 작성해야함! 위에(제트팩 5번) 설명 있음..
//                       3. navigation_header.xml, navigation.manu.xml 만들고 화면 구성
//                       4. activity_main에서 그 메뉴가 선택되었을 때 이벤트 처리

//                  5) ExtendedFloationActionButton: 떠 있는 버튼
//                          - themes.xml에서 Appcomt을 사용하면 오류남! 먼저 이 파일 확인하자
//                          - activity_main.xml에 태그 추가
//                          - 이벤트 처리 하고 싶다면 MainActivity.kt에!!
//
//



//myapplication13: Intent
//할일 목록       1) activity_main.xml에 Recyclerivew와 Extended Floationg AtingBar
//              2) Recyclerivew에 대한 layout 파일 만들고 레이아웃 꾸미기
//                      item_recyclerview.xml
//              3) Recyclerivew를 위해 MyAdapter.kt 만들고 안에 뷰 홀더와 어댑터 정의
//              4) MainActivity.kt에서 Recyclerivew와 MyAdapter.kt를 연결
//              5) 다른 activity 만들고(AddActivity) 2개 연결을 위해 floating버튼에 클릭 이벤트를 둬 인텐트 호출
//              6) activity_add.xml 꾸미기(TextView, EditText, ...)
//              7) activity_add.xml에 액션바 오른쪽에 저장 버튼 만들기 위해 menu/menu_add.xml 생성




//                  <<인텐트에서 값을 전달하는 방법!>>
////                      1. 인텐트에서 값 전달: intent.putExtra(이름, 값)
////                      2. 인텐트에서 값 받아오기: intent.get자료형Extra(이름)
//
//                  <<인텐트를 호출하는 함수!>>
//                      1. startActivity(intent)

                            //보내는 값이 있을 때!
//                      2. startActivityForResult(intent, 10) //10: 코드

//                  <<인텐트가 되돌아 오는 함수!>>
//                      1. onOptionsItemSelected()

                        //보내는 값이 있을 때!
//                      2. startActivityForResult(intent, 10) //10: 코드


//myapplication13: ActivityResultLauncher
//                   <<인텐트를 호출하는 함수!>>
//                      1. requestLauncher.launch((intent))

//                  <<인텐트가 되돌아 오는 함수!>>
//                      1. val requestLauncher: ActivityResultLauncher<Intent>
//        = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//}


//AddActivity
//옵션 메뉴 만들기 onCreateOptionsMenu
//옵션 메뉴 이벤트 처리 onOptionsItemSelected

//Menifest.kt
//인텐트 필터 사용해 Menifest.kt에서 암시적 인텐트 호출하기

//activity의 객체가 소멸되면 데이터가 리셋되는 상태 해결
//MainActivity.kt에서 onSaveInstanceState() 오버라이딩
//
