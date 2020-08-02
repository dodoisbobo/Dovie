package com.dodosetio.dovie.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dodosetio.dovie.R
import com.dodosetio.dovie.sign_in_out.signin.SignIn
import kotlinx.android.synthetic.main.activity_onboarding3.*

class Onboarding3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding3)

        btn_start.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this@Onboarding3, SignIn::class.java))
        }
    }
}