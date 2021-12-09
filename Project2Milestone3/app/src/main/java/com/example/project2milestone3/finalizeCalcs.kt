package com.example.project2milestone3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup

class finalizeCalcs : AppCompatActivity() {
    lateinit var generationGroup:RadioGroup
    lateinit var weatherGroup:RadioGroup
    var hitCount: Int = 0
    var weather:Int = 0

    lateinit var mon1 :Mon
    lateinit var mon2:Mon
    var attBP:Int = 0
    var attType:Int = 0
    var isSpecial:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finalize_calcs)
        mon1 = intent.getSerializableExtra("attMon") as Mon
        mon2 = intent.getSerializableExtra("recMon") as Mon
        attBP = intent.getIntExtra("attBP",0)
        attType = intent.getIntExtra("attType",0)
        isSpecial = intent.getBooleanExtra("isSpecial",false)


    }

    fun genEdit(view: View){
        when(view.id){
            R.id.hit1 -> hitCount = 1
            R.id.hit2 -> hitCount = 2
            R.id.hit3 -> hitCount = 3
            R.id.hit4 -> hitCount = 4
            R.id.hit5 -> hitCount = 5
        }
    }
    fun weatherEdit(view: View){
        when(view.id){
            R.id.NoWeather -> weather = 0
            R.id.Sun -> weather = 1
            R.id.Rain -> weather = 2
            R.id.Sand -> weather = 3
        }
    }

    fun buttonPress(view: View){
        if (view.id == R.id.returnMainButton){
            finish()
        }else if(view.id == R.id.calcRangeButton){
            val finalCalc = Intent(this,rangePage::class.java)
            finalCalc.putExtra("attMon",mon1)
            finalCalc.putExtra("recMon",mon2)
            finalCalc.putExtra("attBp",attBP)
            finalCalc.putExtra("attType",attType)
            finalCalc.putExtra("isSpecial",isSpecial)
            finalCalc.putExtra("hitCount",hitCount)
            finalCalc.putExtra("weatherStatus",weather)
            startActivity(finalCalc)

        }
    }
}