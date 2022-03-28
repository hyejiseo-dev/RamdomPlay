package com.hyejis.randomplay

import android.app.Application
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.hyejis.randomplay.todayeat.EatList
import java.util.*

class EatDialog(mContext: Context) : Dialog(mContext) {
    private val viewModel:EatViewModel = EatViewModel(mContext.applicationContext as Application)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.eat_dialog)

        // 다이얼로그의 배경을 투명으로 만든다.
        Objects.requireNonNull(window)?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val button_save : Button = findViewById(R.id.button_save)
        val button_cancel : Button = findViewById(R.id.button_cancel)
        val food_category : EditText = findViewById(R.id.food_category)
        val food : EditText = findViewById(R.id.food)

        // 버튼 리스너 설정
        button_save.setOnClickListener {
            // '확인' 버튼 클릭시 data insert
            viewModel.insert(
                EatList(food_category.text.toString(),
                    food.text.toString())
            )
            // Custom Dialog 종료
            dismiss()
        }
        button_cancel.setOnClickListener {
            // '취소' 버튼 클릭시
            // Custom Dialog 종료
            dismiss()
        }

    }

}