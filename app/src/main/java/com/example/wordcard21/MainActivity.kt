package com.example.wordcard21

import android.os.Bundle
import android.util.Log
import android.widget.*
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
        val listView: ListView = findViewById(R.id.wordList)

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
                Toast.makeText(this@MainActivity, "Fill both fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val text = "$targetWord: $nativeWord added"

            Executors.newSingleThreadExecutor().execute {
                wordPairDao.insertAll(WordPair(uid, nativeWord, targetWord))
                val users: List<WordPair> = wordPairDao.getAll()
                Log.d("STATE", users.toString())
                val textView = TextView(this)
                textView.text = text
                // listView.addView(textView)
            }

            Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
        }
    }
}