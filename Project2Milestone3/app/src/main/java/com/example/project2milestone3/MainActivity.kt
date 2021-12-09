package com.example.project2milestone3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    lateinit var attackBasePower: EditText
    lateinit var attackType: Spinner
    lateinit var isSpecial: Switch
    lateinit var attackingMon:Mon
    lateinit var receivingMon:Mon

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        attackBasePower = findViewById(R.id.basePowerNumber)
        attackType = findViewById(R.id.attackType)
        isSpecial = findViewById(R.id.specialPhysicalToggle)

        attackingMon = Mon(true,220,0,0,10,12)
        receivingMon = Mon(false,321,186,0,10,12)

        setData()
    }

    fun setData(){
        //attackingMon
        findViewById<TextView>(R.id.attStat).text  = resources.getString(R.string.attStat) + " " + attackingMon.stat1.toString()
        findViewById<TextView>(R.id.attType1).text  = resources.getString(R.string.attackType1) + " " + resources.getStringArray(R.array.types)[attackingMon.type1]
        if(attackingMon.type2 == 0){
            findViewById<TextView>(R.id.attType2).text  = resources.getString(R.string.attackType2) + " N/A"
        }else{
            findViewById<TextView>(R.id.attType2).text  = resources.getString(R.string.attackType2) + " " + resources.getStringArray(R.array.types)[attackingMon.type2]
        }
        findViewById<TextView>(R.id.attItem).text = resources.getString(R.string.attackItem) + " " + resources.getStringArray(R.array.attackingItemList)[attackingMon.item]

        //receivingMon
        findViewById<TextView>(R.id.hpStat).text  = resources.getString(R.string.hpStat) + " " + receivingMon.stat1.toString()
        findViewById<TextView>(R.id.defenseStat).text  = resources.getString(R.string.defStat) + " " + receivingMon.stat2.toString()

        findViewById<TextView>(R.id.recType1).text  = resources.getString(R.string.recType1) + " " + resources.getStringArray(R.array.types)[receivingMon.type1]
        if(receivingMon.type2 == 0){
            findViewById<TextView>(R.id.recType2).text  = resources.getString(R.string.recType2) + " N/A"
        }else{
            findViewById<TextView>(R.id.recType2).text  = resources.getString(R.string.recType2) + " " + resources.getStringArray(R.array.types)[receivingMon.type2]
        }
        findViewById<TextView>(R.id.recItem).text = resources.getString(R.string.recItem) + " " + resources.getStringArray(R.array.receivingItemList)[receivingMon.item]
    }

    fun editButtonClicked(view: View){
        if(view.id == R.id.editAtt){
            val intent = Intent(this, EditAttackMon::class.java)
            intent.putExtra("mon",attackingMon)
            startActivityForResult(intent,0)
        }else if(view.id == R.id.editRec){
            val intent = Intent(this, editReceivingMon::class.java)
            intent.putExtra("mon",receivingMon)
            startActivityForResult(intent,1)
        }
    }

    fun calculate(view: View){
        var layoutRoot = findViewById<ConstraintLayout>(R.id.rootLayout)
        if(attackBasePower.text.isEmpty()){
            val sb = Snackbar.make(layoutRoot,"Please Enter Base Power", Snackbar.LENGTH_SHORT)
            sb.show()
            return
        }else if(attackType.selectedItemPosition == 0){
            val sb = Snackbar.make(layoutRoot,"Please Select Attack Type", Snackbar.LENGTH_SHORT)
            sb.show()
            return
        }


        val intent = Intent(this, finalizeCalcs::class.java)
        intent.putExtra("attMon",attackingMon)
        intent.putExtra("recMon",receivingMon)
        intent.putExtra("attBP",attackBasePower.text.toString().toInt())
        intent.putExtra("attType",attackType.selectedItemPosition)
        intent.putExtra("isSpecial",isSpecial.isChecked)

        startActivityForResult(intent,2)
    }

    fun applyData(isAttacking:Boolean,data: Intent?){
        if(data != null){
            if(data?.let{data.getIntExtra("type1",0)} == 0 && data?.let{data.getIntExtra("type2",0)} != 0){
                if(isAttacking){
                    attackingMon.type1 = data.getIntExtra("type2",0)
                    attackingMon.type2 = 0
                }else{
                    receivingMon.type1 = data.getIntExtra("type2",0)
                    attackingMon.type2 = 0
                }
            }else if(data?.let{data.getIntExtra("type1",0)} != 0 && data?.let{data.getIntExtra("type2",0)} != 0){
                if(isAttacking){
                    attackingMon.type1 = data.getIntExtra("type1",0)
                    attackingMon.type2 = data.getIntExtra("type2",0)
                }else{
                    receivingMon.type1 = data.getIntExtra("type1",0)
                    receivingMon.type2 = data.getIntExtra("type2",0)
                }
            }
            if(isAttacking){
                attackingMon.stat1 = data.getIntExtra("stat1",1)
                attackingMon.item = data.getIntExtra("itemSelected",0)
            }else{
                receivingMon.item = data.getIntExtra("itemSelected",0)
                receivingMon.stat1 = data.getIntExtra("stat1",1)
                receivingMon.stat2 = data.getIntExtra("stat2",1)
            }
            setData()
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            if(requestCode == 0){
                applyData(true,data)
            }else if(requestCode == 1){
                applyData(false,data)
            }

        }
    }


}