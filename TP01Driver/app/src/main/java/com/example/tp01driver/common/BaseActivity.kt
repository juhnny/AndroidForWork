package com.example.tp01driver.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tp01driver.R

open class BaseActivity: AppCompatActivity(), BaseContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startTransition()
    }

    override fun finish() {
        super.finish()
        endTransition()
    }

    fun startTransition(){
        overridePendingTransition(R.anim.slide_left_in, R.anim.slide_left_scale_out)
    }

    fun endTransition(){
        overridePendingTransition(R.anim.slide_right_scale_in, R.anim.slide_right_out)
    }

    override fun startIndicator() {
        TODO("Not yet implemented")
    }

    override fun stopIndicator() {
        TODO("Not yet implemented")
    }
}