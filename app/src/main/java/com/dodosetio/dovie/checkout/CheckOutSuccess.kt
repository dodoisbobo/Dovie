package com.dodosetio.dovie.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dodosetio.dovie.R
import com.dodosetio.dovie.home.HomeActivity
import kotlinx.android.synthetic.main.activity_check_out_success.*

class CheckOutSuccess : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_out_success)

        btn_done.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this@CheckOutSuccess, HomeActivity::class.java))
        }
    }
}