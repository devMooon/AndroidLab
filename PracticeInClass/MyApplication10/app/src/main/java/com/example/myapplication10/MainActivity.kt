package com.example.myapplication10

import android.app.AlertDialog
import android.app.DatePickerDialog
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
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            //Toast.makeText(this, "첫번째 버튼의 토스트입니다.", Toast.LENGTH_LONG).show()
            val toast = Toast.makeText(this, "첫번째 버튼의 토스트입니다.", Toast.LENGTH_LONG)
            toast.setText("수정된 토스트 입니다.")
            toast.duration = Toast.LENGTH_SHORT
            //API 30 > toast.setGravity(Gravity.Top, 20, 20)

            toast.addCallback(
                object:Toast.Callback(){
                    override fun onToastHidden() {
                        super.onToastHidden()
                        Log.d("mobileApp", "토스트가 사라집니다.")
                    }

                    override fun onToastShown() {
                        super.onToastShown()
                        Log.d("mobileApp", "토스트가 나타납니다.")
                    }
                }
            )
            toast.show()
        }

        binding.button2.setOnClickListener {
            DatePickerDialog(this,
                object:DatePickerDialog.OnDateSetListener {
                    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                        //TODO("Not yet implemented")
                        Log.d("mobileApp", "${p1}년, ${p2+1}월, ${p3}일")
                    }
                },
                2022, 2, 30).show() // month 0~11
        }
        binding.button3.setOnClickListener {
            TimePickerDialog(this,
                object:TimePickerDialog.OnTimeSetListener{
                    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
                        //TODO("Not yet implemented")
                        Log.d("mobileApp", "${p1}시 ${p2}분")
                    }
                },
                13, 0, true).show() // true 24, false 12
        }
        val eventHandler = object:DialogInterface.OnClickListener{
            override fun onClick(p0: DialogInterface?, p1: Int) {
                //TODO("Not yet implemented")
                if(p1==DialogInterface.BUTTON_POSITIVE){
                    Log.d("mobileApp", "POSITIVE BUTTON")
                }
                else if(p1==DialogInterface.BUTTON_NEGATIVE){
                    Log.d("mobileApp", "NEGATIVE BUTTON")
                }
            }
        }
        binding.button4.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("알림창 테스트")
                setIcon(android.R.drawable.ic_dialog_info)
                setMessage("정말 종료하시겠습니까?")
                setPositiveButton("YES", eventHandler)
                setNegativeButton("NO", eventHandler)
                setNeutralButton("More", null) //null 자리에 리스너가 들어오는데, 이벤트 처리를 안 할거라 null처리
                setCancelable(false) // 뒤로가기 버튼을 눌렀을 때 창 안 닫힘
                show()
            }.setCanceledOnTouchOutside(false) // 화면 바깥을 눌렀을 때 창 안 닫힘
        }

        val items = arrayOf<String>("사과", "딸기", "복숭아", "토마토")
        binding.button5.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("아이템 목록 선택")
                setIcon(android.R.drawable.ic_dialog_info)
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
        binding.button6.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("멀티 아이템 목록 선택")
                setIcon(android.R.drawable.ic_dialog_info)
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
        binding.button7.setOnClickListener {
            AlertDialog.Builder(this).run {
                setTitle("싱글 아이템 목록 선택")
                setIcon(android.R.drawable.ic_dialog_info)
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