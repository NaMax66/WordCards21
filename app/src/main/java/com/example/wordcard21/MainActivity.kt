package com.example.wordcard21

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd: Button = findViewById(R.id.btnAdd)
        val editTextTargetLng : EditText = findViewById(R.id.editTextTargetLng)
        val editTextNativeLng : EditText = findViewById(R.id.editTextNativeLng)

        btnAdd.setOnClickListener {
            var msg: String = ""

            val targetWord: String = editTextTargetLng.text.toString()
            val nativeWord: String = editTextNativeLng.text.toString()

            if (targetWord == "" || nativeWord == "") {
                msg = "Заполните оба поля"
            }
            else {
                msg = "$targetWord: $nativeWord"

            }

            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
        }
    }
}