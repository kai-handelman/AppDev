package com.example.lab_8

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Switch
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    private var placeName : String? = null
    private var placeMain : String? = null
    private var placeUrl : String? = null

    lateinit var title : TextView
    lateinit var mainItem : TextView
    lateinit var approve : Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        title = findViewById(R.id.resturantName)
        approve = findViewById(R.id.foodToggle)
        mainItem = findViewById(R.id.mainItem)
        placeName = intent.getStringExtra("PlaceName")
        placeMain = intent.getStringExtra("PlaceItem")
        placeUrl = intent.getStringExtra("PlaceUrl")

        placeName?.let{ title.text = placeName}
        placeMain?.let{ mainItem.text = placeMain}
    }




    fun returnToMain(view: View){
        val data = intent
        data.putExtra("Liked",approve.isChecked.toString())
        setResult(Activity.RESULT_OK,data)
        finish()
    }

    fun openURL(view: View){
        var intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.data = placeUrl?.let{ Uri.parse(placeUrl)}
        startActivity(intent)
    }
}