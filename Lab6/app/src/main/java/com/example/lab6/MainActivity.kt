package com.example.lab6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun clickedButton(view: View){
        val newText = findViewById<TextView>(R.id.messageToUser)
        val name = findViewById<EditText>(R.id.editTextName)
        newText.text = "Merry Christmas " + name.text + "!!"
        name.getText().clear();
        val tree = findViewById<ImageView>(R.id.treeImage)
        tree.alpha = 1f
    }
}