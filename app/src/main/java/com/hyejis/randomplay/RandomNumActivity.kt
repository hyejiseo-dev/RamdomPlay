package com.hyejis.randomplay

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.hyejis.randomplay.databinding.ActivityNumBinding

class RandomNumActivity: AppCompatActivity(){

    private lateinit var binding: ActivityNumBinding
    var resultList = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_num)

        var startNum = binding.etStart?.text
        var endNum = binding.etEnd?.text
        var countNum = binding.etCount?.text

        var num:Int = 1

        binding.applyBtn.setOnClickListener {
            if(startNum.isNotEmpty() && endNum.isNotEmpty() && countNum.isNotEmpty()){  //둘중 하나 비었을때
                val a = Integer.parseInt(startNum.toString())
                val b = Integer.parseInt(endNum.toString())
                val c = Integer.parseInt(countNum.toString())
                val count = (b-a)+1 //횟수 제한 비교를 위함

                if(a < b && c <= count){  //모든 값이 잘 입력되었을때
                    val pickNum = (a..b).random().toString()
                    if(num <= c){
                        if(!resultList.contains(pickNum)){
                            binding.tvResult.text = "$num 번째 당첨번호 : $pickNum"
                            resultList.add(pickNum)
                            binding.tvResultList.text = "지금까지 당첨된 번호: $resultList"
                            num++;
                        }else{
                            Toast.makeText(this, "이미 뽑힘 : $pickNum", Toast.LENGTH_SHORT).show()
                        }
                    }else{
                        Toast.makeText(this, "당첨이 끝났습니다! 축하해요~", Toast.LENGTH_SHORT).show()
                        num = 1
                        binding.applyBtn.isEnabled = false
                    }
                }else if(a >= b){ //시작이 더 클때
                    Toast.makeText(this, "시작번호는 끝번호보다 작아야 합니다", Toast.LENGTH_SHORT).show()
                }else if(c >= count){  //횟수가 더 클때
                    Toast.makeText(this, "추첨 횟수는 범위 $count 보다 작아야 합니다", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "빈 값을 입력해주세요!", Toast.LENGTH_SHORT).show()
            }
        }

        binding.resetBtn.setOnClickListener {
            resultList.clear()

            binding.tvResult.text = "당첨번호 : "
            binding.tvResultList.text = "지금까지 당첨된 번호 : "
            num = 1
            binding.applyBtn.isEnabled = true
        }

        binding.tvResultList.movementMethod = ScrollingMovementMethod()

    }
}