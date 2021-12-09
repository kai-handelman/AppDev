package com.example.project2milestone3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlin.math.floor

class rangePage : AppCompatActivity() {
    var hitCount: Int = 0
    var weather:Int = 0

    lateinit var mon1 :Mon
    lateinit var mon2:Mon
    var attBP:Int = 0
    var attType:Int = 0
    var isSpecial:Boolean = false
    lateinit var normal:TextView
    lateinit var crit:TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_range_page)
        mon1 = intent.getSerializableExtra("attMon") as Mon
        mon2 = intent.getSerializableExtra("recMon") as Mon
        attBP = intent.getIntExtra("attBp",0)
        attType = intent.getIntExtra("attType",0)
        isSpecial = intent.getBooleanExtra("isSpecial",false)
        hitCount = intent.getIntExtra("hitCount",1)
        weather = intent.getIntExtra("weatherStatus",0)

        normal = findViewById(R.id.regularDamageRange)
        crit = findViewById(R.id.critDamageRange)
        calcTheRoles()
    }
    private fun calcTheRoles(){
        var aPart = 42
        var power:Float = attBP.toFloat()
        if(mon1.item == 3){
            power *= 1.5f
        }

        var attStat = mon1.stat1.toDouble()
        var defStat = mon2.stat2.toDouble()
        if(mon1.item == 2){
            attStat *= 1.5f
        }
        if(mon2.item == 1 || (isSpecial && mon2.item == 3) || (weather == 3 && isSpecial && (mon2.type1 == 16 || mon2.type2 == 16))){
            defStat *= 1.5f
        }
        var ad:Float = (floor(attStat) / floor(defStat)).toFloat()

        var initialPower:Float = ((aPart*power*ad)/50)+2
        if(attType == mon1.type1 || attType == mon1.type2 ){
            initialPower *= 1.5f
        }

        var matchUpMultiplier = TypeMatchUp().getTypeChart(mon2.type1,attType)
        if(mon2.type2 != 0){
            matchUpMultiplier *= TypeMatchUp().getTypeChart(mon2.type2, attType)
        }
        if((weather == 2 && attType == 18)||(weather == 1 && attType ==7)){
            matchUpMultiplier *= 2
        }else if((weather == 1 && attType == 18)||(weather == 2 && attType ==7)){
            matchUpMultiplier *= 0.5f
        }
        if(mon2.item == 2){
            matchUpMultiplier *= 0.5f
        }

        if(mon1.item == 1){
            matchUpMultiplier *= 1.3f
        }
        initialPower = floor(initialPower * matchUpMultiplier)

        var upperBound: Float = floor(initialPower*hitCount)
        var lowerBound: Float = floor(initialPower * 0.85f*hitCount)

        normal.text = (floor((lowerBound*100)/ mon2.stat1.toFloat()) /100).toString() + "% ~ " + (floor((upperBound*100)/ mon2.stat1.toFloat()) /100).toString() + "%"
        crit.text = (floor((lowerBound*1.5f*100)/ mon2.stat1.toFloat()) /100).toString()+ "% ~ " + (floor((1.5f * upperBound*100)/ mon2.stat1.toFloat()) /100).toString()  + "%"

    }

    fun backtoFinalize(view: View){
        finish()
    }
}
