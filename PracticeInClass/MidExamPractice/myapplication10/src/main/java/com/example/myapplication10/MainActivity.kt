package com.example.myapplication10

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Notification
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.myapplication10.databinding.ActivityMainBinding
import com.example.myapplication10.databinding.DialogInputBinding

class MainActivity : AppCompatActivity() {
    //퍼미션 설정
//    1. 보호하려는 컴포넌트를 menifests 파일에 <permission> 설정
//    2. 보호하려는 컴포넌트에 android:permission 속성 설정
//
//    3. 해당 컴포넌트를 이용하는 측의 menifests 파일에 <user-permission> 설정

    //버전 호환성
//    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
//        val builder: Notification.Builder = Notification.Builder(this, "1")
//            .setStyle(
//                Notification.CallStyle.forIncomingCall(caller, declineIntent, answerIntent)
//            )
//    }
    //@TargetApi(Build.VERSION_CODES.S)
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //토스트 Toast
        binding.button1.setOnClickListener {
            //Toast.makeText(this, "첫번째 버튼의 토스트입니다.", Toast.LENGTH_LONG).show()

            //토스트 생성
            val toast = Toast.makeText(this, "첫번째 버튼의 토스트입니다.", Toast.LENGTH_LONG)
            //토스트 글씨 수정
            toast.setText("수정된 토스트 입니다.")
            //토스트 유지 시간 수정
            toast.duration = Toast.LENGTH_SHORT
            //API 30 > toast.setGravity(Gravity.Top, 20, 20)

            //토스트 콜백함수 추가
            toast.addCallback(
                //Toast.Callback 타입 객체를 토스트 객체의 addCallback() 함수로 등록하면 됨
                //object: 익명클래스(선언과 동시에 객체 생성)
                //:Toast.Callback(): 상속받는 상위 클래스
                object:Toast.Callback(){
                    //토스트가 화면에서 사라지는 순간 호출
                    override fun onToastHidden() {
                        super.onToastHidden()
                        Log.d("mobileApp", "토스트가 사라집니다.")
                    }

                    //토스트가 화면에 타나나는 순간 호출
                    override fun onToastShown() {
                        super.onToastShown()
                        Log.d("mobileApp", "토스트가 나타납니다.")
                    }
                }
            )
            //토스트 보이기
            toast.show()
        }


        //데이트 피커 다이얼로그
        binding.button2.setOnClickListener {
            DatePickerDialog(this,
                object: DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                        //TODO("Not yet implemented")
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
                object:TimePickerDialog.OnTimeSetListener{
                    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                        //TODO("Not yet implemented")
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
                if(p1==DialogInterface.BUTTON_POSITIVE){
                    Log.d("mobileApp", "POSITIVE BUTTON")
                }
                else if(p1==DialogInterface.BUTTON_NEGATIVE){
                    Log.d("mobileApp", "NEGATIVE BUTTON")
                }
                //BUTTON_NEUTRAL
            }
        }
        binding.button4.setOnClickListener {
            //AlertDialog의 생성자는 pretected로 선언 되어서 객체를 직접 생성할 수 없고,
            //AlertDialog.Builder()를 이용해 생성
            AlertDialog.Builder(this).run {
                //알림창 제목 지정
                setTitle("알림창 테스트")
                //알림창 아이콘 지정
                setIcon(android.R.drawable.ic_dialog_info)
                //알림창 내용 지정
                setMessage("정말 종료하시겠습니까?")

                //알림창 버튼 지정
                setPositiveButton("YES", eventHandler)
                setNegativeButton("NO", eventHandler)
                setNeutralButton("More", null) //null 자리에 리스너가 들어오는데, 이벤트 처리를 안 할거라 null처리
                setCancelable(false) // 뒤로가기 버튼을 눌렀을 때 창 안 닫힘
                show()
            }.setCanceledOnTouchOutside(false) // 화면 바깥을 눌렀을 때 창 안 닫힘
        }

        //Alert Dialog의 내용을 아이템(setItem), 체크박스(setMultiChoiceItems), 라디오버튼(setSingleChoiceItems)으로 구성

        //배열 만들기
        val items = arrayOf<String>("사과", "딸기", "복숭아", "토마토")

        //Alert Dialog setItem [아이템 선택 다이얼로그]
        binding.button5.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("아이템 목록 선택")
                setIcon(android.R.drawable.ic_dialog_info)

                //아이템 선택 다이얼로그
                setItems(items, object:DialogInterface.OnClickListener{
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        //TODO("Not yet implemented")
                        Log.d("mobileApp", "${items[p1]}")
                    }
                })
                setPositiveButton("닫기", null)
                setCancelable(false) // 뒤로가기 버튼을 눌렀을 때 창 안 닫힘
                show()
            }.setCanceledOnTouchOutside(false) // 화면 바깥을 눌렀을 때 창 안 닫힘
        }

        //Alert Dialog setMultiChoiceItems [체크박스 다이얼로그]
        binding.button6.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("멀티 아이템 목록 선택")
                setIcon(android.R.drawable.ic_dialog_info)
                //체크박스 다이얼로그로 내용 변경
                setMultiChoiceItems(items, booleanArrayOf(false, true, false, false),// 두번째 인자: 초기값
                    object:DialogInterface.OnMultiChoiceClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int, p2: Boolean) {
                            //TODO("Not yet implemented")
                            Log.d("mobileApp", "${items[p1]} ${if(p2) "선택" else "해제"}")
                        }
                    }
                )
                setPositiveButton("닫기", null)
                show()
            }
        }

        //Alert Dialog setSingleChoiceItems [라디오 버튼 다이얼로그]
        binding.button7.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("싱글 아이템 목록 선택")
                setIcon(android.R.drawable.ic_dialog_info)
                //라디오 버튼으로 다이얼로그 내용 변경
                setSingleChoiceItems(items, 1, //두번째 인자 : 몇번째 인자를 초기에 true? 1 -> 두번째꺼
                    object:DialogInterface.OnClickListener{
                        override fun onClick(p0: DialogInterface?, p1: Int) {
                            //TODO("Not yet implemented")
                            Log.d("mobileApp", "${items[p1]}")
                        }
                    }
                )
                setPositiveButton("닫기", null)
                show()
            }
        }

        //커스텀 다이얼로그
        //layout/dialog_input.XML 파일 제작
        //layoutInflater를 통해 바인딩 객체로 변환
        val dialogBinding = DialogInputBinding.inflate(layoutInflater) // tag를 뷰 객체로 변환
        val alert = AlertDialog.Builder(this)
            .setTitle("입력")
            .setView(dialogBinding.root)
            .setPositiveButton("닫기", null)
            .create()
        binding.button8.setOnClickListener {
            alert.show()
        }
    }
}