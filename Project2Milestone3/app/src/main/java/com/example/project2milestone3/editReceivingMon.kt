package com.example.project2milestone3

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Spinner

class editReceivingMon : AppCompatActivity() {
    lateinit var hpInput: EditText
    lateinit var defInput: EditText
    lateinit var type1Input: Spinner
    lateinit var type2Input: Spinner
    lateinit var itemInput: RadioGroup
    lateinit var mon:Mon

    var item:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_receiving_mon)
        mon = intent.getSerializableExtra("mon") as Mon
        hpInput = findViewById(R.id.recHpStat)
        defInput = findViewById(R.id.recDefStat)
        type1Input = findViewById(R.id.receiveType1)
        type2Input = findViewById(R.id.receiveType2)
        itemInput = findViewById(R.id.receivingItems)

        if(mon != null){
            hpInput.setText(mon.stat1.toString())
            defInput.setText(mon.stat2.toString())
            type1Input.setSelection(mon.type1)
            type2Input.setSelection(mon.type2)
            when(mon.item){
                0 -> itemInput.check(R.id.NoItems)
                1 -> itemInput.check(R.id.ItemEviolite)
                2 -> itemInput.check(R.id.ItemBerry)
                3 -> itemInput.check(R.id.ItemAV)
            }
        }

    }

    fun itemEdit(view: View){
        item = view.id
        when(view.id){
            R.id.NoItems -> item = 0
            R.id.ItemEviolite -> item = 1
            R.id.ItemBerry -> item = 2
            R.id.ItemAV -> item = 3

        }
    }

    fun returnBackToMain(view: View){
        val data = intent
        if(!hpInput.text.isEmpty()){
            data.putExtra("stat1",hpInput.text.toString().toInt())
        }else{
            data.putExtra("stat1",1)
        }
        if(!defInput.text.isEmpty()){
            data.putExtra("stat2",defInput.text.toString().toInt())
        }else{
            data.putExtra("stat2",1)
        }
        data.putExtra("type1",type1Input.selectedItemPosition)
        data.putExtra("type2",type2Input.selectedItemPosition)
        data.putExtra("itemSelected",item)


        setResult(Activity.RESULT_OK,data)
        finish()
    }
}