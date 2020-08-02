package com.dodosetio.dovie.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dodosetio.dovie.R
import kotlinx.android.synthetic.main.activity_onboarding2.*

class Onboarding2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding2)

        btn_next.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this@Onboarding2, Onboarding3::class.java))
        }
    }
}