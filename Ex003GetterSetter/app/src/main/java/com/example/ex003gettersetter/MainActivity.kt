package com.example.ex003gettersetter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val animal = Animal()
//        animal.name = "Pinky"
        Log.e("TAG", animal.name)
        Log.e("TAG", animal.age)
        Log.e("TAG", "${animal.counter}")
        animal.counter = 5
        Log.e("TAG","${animal.counter}")
        animal.counter = -3
        Log.e("TAG", "${animal.counter}")

    }
}