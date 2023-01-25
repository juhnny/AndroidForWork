package com.example.tp01driver.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.tp01driver.R
import com.example.tp01driver.common.BaseActivity
import com.example.tp01driver.data.UserVO
import com.example.tp01driver.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity(), LoginContract.View {

    val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    val presenter by lazy { LoginPresenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonLogin.setOnClickListener {
            var email = binding.editEmail.text.toString()
            var pw = binding.editPw.text.toString()
            var params = HashMap<String, String>()
            params.put("userId", email)
            params.put("password", pw)
//            params["userId"] = email
//            params["password"] = pw
            Log.e("LoginAct buttonLogin", "${params.toString()}")
            presenter.driverLogin(this, params)
//            presenter.driverLogin(this, email, pw)
        }
    }

    override fun loginSuccess(userInfo: UserVO?) {
        Toast.makeText(this, "Login Success - ${userInfo?.tdId}", Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun loginFail(message: String?) {
        Toast.makeText(this, "Login Failure", Toast.LENGTH_SHORT).show()
    }
}