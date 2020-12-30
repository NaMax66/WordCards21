package com.example.wordcard21

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.*
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnAdd: Button = findViewById(R.id.btnAdd)
        val editTextTargetLng : EditText = findViewById(R.id.editTextTargetLng)
        val editTextNativeLng : EditText = findViewById(R.id.editTextNativeLng)

        val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "users"
        ).build()

        val userDao = db.userDao()

        var uid = 10

        btnAdd.setOnClickListener {

            val targetWord: String = editTextTargetLng.text.toString()
            val nativeWord: String = editTextNativeLng.text.toString()


            val msg = if (targetWord == "" || nativeWord == "") {
                "Заполните оба поля"
            } else {
                "$targetWord: $nativeWord"

            }

            Executors.newSingleThreadExecutor().execute {
                userDao.insertAll(User(uid, "Max", "Naidovich"))
                val users: List<User> = userDao.getAll()
                Log.d("STATE", users.toString())
                uid++
            }


            Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
        }
    }
}