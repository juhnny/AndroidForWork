package com.example.ex001okhttp3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val tv:TextView by lazy { findViewById(R.id.main_text) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        tv.text = "HIIIIII"

//        run("https://www.naver.com")

//        setGetFun()

        setGetFunWithInterceptor()
    }

    fun run(url:String){
        val client:OkHttpClient = OkHttpClient()
        val request:Request = Request.Builder()
            .url(url)
            .build()
        try {
            val response:Response = client.newCall(request).execute()
            var text:String = response.body.string()
            runOnUiThread {
                tv.text = text
            }
        } catch (e: IOException){
        }
    }

    fun setGetFun(){
        var url = "https://www.google.com"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: okio.IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    tv.text = response.body.string()
                }
            }
        })
    }

    fun setGetFunWithInterceptor(){
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC

        var url = "https://www.google.com"
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val request = Request.Builder().url(url).build()

        client.newCall(request).enqueue(object : Callback{
            override fun onFailure(call: Call, e: okio.IOException) {
            }

            override fun onResponse(call: Call, response: Response) {
                runOnUiThread {
                    tv.text = response.body.string()
                }
            }
        })
    }

}