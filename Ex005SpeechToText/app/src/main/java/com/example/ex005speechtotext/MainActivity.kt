package com.example.ex005speechtotext

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.speech.RecognitionListener
import android.speech.RecognizerIntent
import android.speech.SpeechRecognizer
import android.speech.tts.TextToSpeech
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import java.util.Locale


class MainActivity : AppCompatActivity() {
    var cThis //context 설정
            : Context? = null

    //음성 인식용
    var SttIntent: Intent? = null
    var mRecognizer: SpeechRecognizer? = null

    //음성 출력용
    var tts: TextToSpeech? = null

    // 화면 처리용
    var btnSttStart: Button? = null
    var txtInMsg: EditText? = null
    var txtSystem: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        cThis = this
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecognizerIntent 생성
        SttIntent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
        SttIntent!!.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, applicationContext.packageName)
        SttIntent!!.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR") //한국어 사용
        // 새 SpeechRecognizer 를 만드는 팩토리 메서드
        mRecognizer = SpeechRecognizer.createSpeechRecognizer(cThis)
        mRecognizer?.setRecognitionListener(listener)

        //음성출력 생성, 리스너 초기화
        tts = TextToSpeech(cThis) { status ->
            if (status != TextToSpeech.ERROR) {
                tts!!.language = Locale.KOREAN
            }
        }

        //버튼설정
        btnSttStart = findViewById(R.id.btn_stt_start)
        btnSttStart?.setOnClickListener(object : OnClickListener {
            override fun onClick(p0: View?) {
                println("음성인식 시작!")
                if (ContextCompat.checkSelfPermission(cThis!!, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.RECORD_AUDIO), 1)
                    //권한을 허용하지 않는 경우
                } else {
                    //권한을 허용한 경우
                    try {
                        mRecognizer?.startListening(SttIntent)
                    } catch (e: SecurityException) {
                        e.printStackTrace()
                    }
                }
            }
        })

        txtInMsg = findViewById(R.id.txtInMsg)
        txtSystem = findViewById(R.id.txtSystem)
        //어플이 실행되면 자동으로 1초뒤에 음성 인식 시작
        Handler(Looper.getMainLooper()).postDelayed({
            var text = "[onCreate] \n${txtSystem?.text.toString()}"
            txtSystem!!.setText(text)

            btnSttStart!!.performClick()
        }, 1000) //바로 실행을 원하지 않으면 지워주시면 됩니다
    }

    private val listener: RecognitionListener = object : RecognitionListener {
        override fun onReadyForSpeech(bundle: Bundle) {
            var text = "[onReadyForSpeech] \n${txtSystem?.text.toString()}"
            txtSystem?.setText(text)
        }

        override fun onBeginningOfSpeech() {
            var text = "[onBeginningOfSpeech] \n${txtSystem?.text.toString()}"
            txtSystem!!.setText(text)
        }

        override fun onRmsChanged(v: Float) {
//            var text = "[onRmsChanged] ${txtSystem?.text.toString()}"
//            txtSystem!!.setText(text)
        }

        override fun onBufferReceived(bytes: ByteArray) {
            var text = "[onBufferReceived] \n${txtSystem?.text.toString()}"
            txtSystem!!.setText(text)
        }

        override fun onEndOfSpeech() {
            var text = "[onEndOfSpeech] \n${txtSystem?.text.toString()}"
            txtSystem!!.setText(text)
        }

        override fun onError(i: Int) {
            var text = "[onError] 천천히 다시 말해주세요.\n${txtSystem?.text.toString()}"
            txtSystem!!.setText(text)
        }

        override fun onResults(results: Bundle) {
            var key = ""
            key = SpeechRecognizer.RESULTS_RECOGNITION
            val mResult = results.getStringArrayList(key)
            val rs = arrayOfNulls<String>(mResult!!.size)
            mResult.toArray(rs)
            var text = "${rs[0]} \r\n${txtInMsg?.text.toString()}"
            txtInMsg!!.setText(text)

            FuncVoiceOrderCheck(rs[0])
            mRecognizer!!.startListening(SttIntent)
        }

        override fun onPartialResults(bundle: Bundle) {
            var text = "[onPartialResults] \n${txtSystem?.text.toString()}"
            txtSystem!!.setText(text)
        }

        override fun onEvent(i: Int, bundle: Bundle) {
            var text = "[onEvent] \n${txtSystem?.text.toString()}"
            txtSystem!!.setText(text)
        }
    }

    //입력된 음성 메세지 확인 후 동작 처리
    private fun FuncVoiceOrderCheck(VoiceMsg: String?) {
        var VoiceMsg = VoiceMsg
        if (VoiceMsg!!.length < 1) return
        VoiceMsg = VoiceMsg.replace(" ", "") //공백제거
        if (VoiceMsg.indexOf("카카오톡") > -1 || VoiceMsg.indexOf("카톡") > -1) {
            val launchIntent = packageManager.getLaunchIntentForPackage("com.kakao.talk")
            startActivity(launchIntent)
            onDestroy()
        } //카카오톡 어플로 이동
        if (VoiceMsg.indexOf("전등꺼") > -1 || VoiceMsg.indexOf("불꺼") > -1) {
            FuncVoiceOut("전등을 끕니다") //전등을 끕니다 라는 음성 출력
        }
    }


    //음성 메세지 출력용
    private fun FuncVoiceOut(OutMsg: String) {
        if (OutMsg.length < 1) return
        tts!!.setPitch(1.0f) //목소리 톤1.0
        tts!!.setSpeechRate(1.0f) //목소리 속도
        tts!!.speak(OutMsg, TextToSpeech.QUEUE_FLUSH, null)

        //어플이 종료할때는 완전히 제거
    }

    //카톡으로 이동을 했는데 음성인식 어플이 종료되지 않아 계속 실행되는 경우를 막기위해 어플 종료 함수
    override fun onDestroy() {
        super.onDestroy()
        if (tts != null) {
            tts!!.stop()
            tts!!.shutdown()
            tts = null
        }
        if (mRecognizer != null) {
            mRecognizer!!.destroy()
            mRecognizer!!.cancel()
            mRecognizer = null
        }
    }
}