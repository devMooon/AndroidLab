package com.example.myapplication2

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.widget.SearchView
import com.example.myapplication2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)

        //제트팩 라이브러리 1) appcompat - Search View 서치뷰! 메뉴 이벤트 처리
        //menu_main.xml에 미리 item 추가하고 서치뷰로 임명 해줘야 함
        //서치뷰 import 확인하기!!
        //android.widget.SearchView라고 적혀있을텐데
        //androidx.appcompat.widget.SearchView 로 변경해야함!!!

        //서치뷰 아이템 가져오기
        val menuSearch = menu?.findItem(R.id.menu_search)
        val searchView = menuSearch?.actionView as SearchView

        //서치뷰에서 사용자가 입력한 값을 가져오는 리스너
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //데이트 피커 다이얼로그
        binding.button2.setOnClickListener {
            DatePickerDialog(this,
                object: DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                        //TODO("Not yet implemented")
                        binding.dateText.text = "선택된 날짜: ${p1}년, ${p2+1}월, ${p3}일"
                        Log.d("mobileApp", "${p1}년, ${p2+1}월, ${p3}일")
                    }
                },
                //화면에 처음 보이는 날짜
                2022, 2, 30).show()
            // month 0~11   : 2는 3월
        }

        //타임 피커 다이얼로그
        binding.button3.setOnClickListener {
            TimePickerDialog(this,
                object: TimePickerDialog.OnTimeSetListener{
                    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                        //TODO("Not yet implemented")
                        binding.timeText.text = "선택된 시간: ${p1}시 ${p2}분"
                        Log.d("mobileApp", "${p1}시 ${p2}분")
                    }
                },
                //화면에 처음 보이는 시간
                13, 0, true).show()
            // true 24시간제, false 12시간제
        }

        //AlertDialog 버튼의 이벤트 핸들러 등록
        val eventHandler = object: DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                //TODO("Not yet implemented")
                if(p1== DialogInterface.BUTTON_POSITIVE){

                    Log.d("mobileApp", "POSITIVE BUTTON")
                }
                else if(p1== DialogInterface.BUTTON_NEGATIVE){
                    Log.d("mobileApp", "NEGATIVE BUTTON")
                }
                //BUTTON_NEUTRAL
            }
        }

        //Alert Dialog의 내용을 아이템(setItem), 체크박스(setMultiChoiceItems), 라디오버튼(setSingleChoiceItems)으로 구성

        //배열 만들기
        val items = arrayOf<String>("1학년", "2학년", "3학년", "4학년")

        //Alert Dialog setSingleChoiceItems [라디오 버튼 다이얼로그]
        binding.button7.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("싱글 아이템 목록 선택")
                setIcon(android.R.drawable.ic_dialog_info)
                //라디오 버튼으로 다이얼로그 내용 변경
                setSingleChoiceItems(items, 1, //두번째 인자 : 몇번째 인자를 초기에 true? 1 -> 두번째꺼
                    object: DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            //TODO("Not yet implemented")
                            binding.timeText.text = "선택된 학년: ${items[p1]}"
                            Log.d("mobileApp", "${items[p1]}")
                        }
                    }
                )
                //알림창 아이콘 지정
                setIcon(android.R.drawable.ic_dialog_info)

                //알림창 버튼 지정
                setPositiveButton("YES", eventHandler)
                setNegativeButton("NO", eventHandler)
                setNeutralButton("More", null) //null 자리에 리스너가 들어오는데, 이벤트 처리를 안 할거라 null처리
                setCancelable(false) // 뒤로가기 버튼을 눌렀을 때 창 안 닫힘

                setPositiveButton("닫기", null)
                show()
            }
        }

    }
}