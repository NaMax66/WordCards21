package com.example.wordcard21

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.*
import java.util.concurrent.Executors
import kotlin.random.Random.Default.nextInt

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

        val wordPairDao = db.wordpairDao()

        val uid = nextInt()

        btnAdd.setOnClickListener {

            val nativeWord: String = editTextNativeLng.text.toString()
            val targetWord: String = editTextTargetLng.text.toString()

            if (targetWord == "" || nativeWord == "") {
                Toast.makeText(this@MainActivity, "Заполните оба поля", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Executors.newSingleThreadExecutor().execute {
                wordPairDao.insertAll(WordPair(uid, nativeWord, targetWord))
                val users: List<WordPair> = wordPairDao.getAll()
                Log.d("STATE", users.toString())
            }

            Toast.makeText(this@MainActivity, "$targetWord: $nativeWord added", Toast.LENGTH_SHORT).show()
        }
    }
}