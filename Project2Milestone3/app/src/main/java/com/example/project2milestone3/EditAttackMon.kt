package com.example.project2milestone3

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner

class EditAttackMon : AppCompatActivity() {
    lateinit var attackInput:EditText
    lateinit var type1Input:Spinner
    lateinit var type2Input:Spinner
    lateinit var itemInput:RadioGroup

    var item:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        var mon:Mon? = null
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_attack_mon)
        mon = intent.getSerializableExtra("mon") as Mon?

        attackInput = findViewById(R.id.attackStatInput)
        type1Input = findViewById(R.id.attackType1)
        type2Input = findViewById(R.id.attackType2)
        itemInput = findViewById(R.id.attackItems)
        if(mon != null){
            attackInput.setText(mon.stat1.toString())
            type1Input.setSelection(mon.type1)
            type2Input.setSelection(mon.type2)
            when(mon.item){
                0 -> itemInput.check(R.id.NoItems)
                1 -> itemInput.check(R.id.ItemLifeOrb)
                2 -> itemInput.check(R.id.ItemChoiced)
                3 -> itemInput.check(R.id.ItemGem)
            }
        }

    }

    fun itemEdit(view:View){
        when(view.id){
            R.id.NoItems -> item = 0
            R.id.ItemLifeOrb -> item = 1
            R.id.ItemChoiced -> item = 2
            R.id.ItemGem -> item = 3
        }
    }

    fun returnBackToMain(view: View){
        val data = intent
        if(!attackInput.text.isEmpty()){
            data.putExtra("stat1",attackInput.text.toString().toInt())
        }else{
            data.putExtra("stat1",1)
        }
        data.putExtra("stat2",0)
        data.putExtra("type1",type1Input.selectedItemPosition)
        data.putExtra("type2",type2Input.selectedItemPosition)
        data.putExtra("itemSelected",item)
        setResult(Activity.RESULT_OK,data)
        finish()
    }

}