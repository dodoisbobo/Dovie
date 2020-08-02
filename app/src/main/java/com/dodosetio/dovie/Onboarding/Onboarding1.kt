package com.dodosetio.dovie.Onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dodosetio.dovie.R
import com.dodosetio.dovie.sign_in_out.signin.SignIn
import com.dodosetio.dovie.util.Preferences
import kotlinx.android.synthetic.main.activity_onboarding1.*

class Onboarding1 : AppCompatActivity() {

    lateinit var preference:Preferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding1)
        preference = Preferences(this)

        if(preference.getValue("onboarding").equals("1")){
            var intent = Intent(this@Onboarding1, SignIn::class.java)
            startActivity(intent)
        }
        btn_next.setOnClickListener {
            finishAffinity()
            var intent = Intent(this@Onboarding1, Onboarding2::class.java)
            startActivity(intent)
        }
        btn_skip.setOnClickListener {
            preference.setValue("onboarding","1")
            finishAffinity()
            var intent = Intent(this@Onboarding1, SignIn::class.java)
            startActivity(intent)
        }
    }
}