package com.example.lab_8

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import com.example.lab_8.FoodPlaces
import com.example.lab_8.R

class MainActivity : AppCompatActivity() {
    lateinit var locationButton : Button
    lateinit var t : TextView
    lateinit var foodSpinner : Spinner
    private var foodSuggests = FoodPlaces()
    private var selectedLocationPosition = 0
    private val RequstCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        locationButton = findViewById(R.id.buttonFood)
        t = findViewById(R.id.topText)
        foodSpinner = findViewById(R.id.spinnerFood)

        locationButton.setOnClickListener {
            selectedLocationPosition = foodSpinner.selectedItemPosition
            foodSuggests.getFood(selectedLocationPosition)

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("PlaceName",foodSuggests.name)
            intent.putExtra("PlaceItem",foodSuggests.main)
            intent.putExtra("PlaceSite",foodSuggests.url)
            startActivityForResult(intent,RequstCode)

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if((requestCode == RequstCode) && (resultCode == Activity.RESULT_OK)) {
            t.setText("Liked: " + data?.let{data.getStringExtra("Liked")})
        }
    }
}