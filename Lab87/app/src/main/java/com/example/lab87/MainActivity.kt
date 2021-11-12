package com.example.lab87

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Spinner
import android.widget.Switch
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickOrderButton(view: View){
        var layoutRoot = findViewById<ConstraintLayout>(R.id.rootLayout)
        val burgCheck = findViewById<CheckBox>(R.id.checkBox1)
        val dogCheck = findViewById<CheckBox>(R.id.checkBox2)
        if (!burgCheck.isChecked && !dogCheck.isChecked){
            val sb = Snackbar.make(layoutRoot,"Choose at least one food option", Snackbar.LENGTH_SHORT)
            sb.show()
            return
        }
        val spin = findViewById<Spinner>(R.id.countSpinner).selectedItem.toString().lowercase()
        val orderText = findViewById<TextView>(R.id.orderText)
        val toggle = findViewById<Switch>(R.id.ToggleSwitch)
        var s = "You have ordered $spin order(s) worth of"
        if(burgCheck.isChecked)
            s = s + " burgers"
        if(dogCheck.isChecked)
            if(burgCheck.isChecked)
                s = s + " and"
            s = s + " hotdogs"
        if(toggle.isChecked){
            s = s + " for to go!"
        }else{
            s = s + " for dine in!"
        }
        orderText.text = s
    }


}