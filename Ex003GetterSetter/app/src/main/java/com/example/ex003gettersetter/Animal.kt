package com.example.ex003gettersetter

import android.util.Log

class Animal {
    var name: String = "NoName"
        get() {
            Log.e("Getter", "$field")
            return "TestName"
        }
        private set

    var age: String = "NoAge"
        get() {
            Log.e("Getter", "$field")
            return "TestAge"
        }
        private set

    var counter = 0
        set(value){
            if(value > 0) field = value
        }


}