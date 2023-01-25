package com.example.tp01driver.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.tp01driver.R
import com.example.tp01driver.common.BaseActivity
import com.example.tp01driver.data.UserVO
import com.example.tp01driver.databinding.ActivitySplashBinding

class SplashActivity : BaseActivity(), LoginContract.View {

    val binding by lazy { ActivitySplashBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Handler().postDelayed(object : Runnable{
            override fun run() {
                startActivity(Intent(this@SplashActivity, LoginActivity::class.java))
                finish()
            }
        }, 2000)

    }

    override fun loginSuccess(userInfo: UserVO?) {
        TODO("Not yet implemented")
    }

    override fun loginFail(message: String?) {
        TODO("Not yet implemented")
    }
}