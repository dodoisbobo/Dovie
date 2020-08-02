package com.dodosetio.dovie.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.dodosetio.dovie.R
import com.dodosetio.dovie.home.HomeActivity
import com.dodosetio.dovie.model.CheckOut
import com.dodosetio.dovie.util.Preferences
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_checkout.back
import kotlinx.android.synthetic.main.activity_checkout.btn_purchase
import kotlinx.android.synthetic.main.activity_choose_seat.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckOutActivity : AppCompatActivity() {

    private var dataList = ArrayList<CheckOut>()
    private var total:Int = 0
    private lateinit var preferences:Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preferences = Preferences(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<CheckOut>

        for (a in dataList.indices){
            total += dataList[a].harga!!.toInt()
        }

        dataList.add(CheckOut("Total ",total.toString()))

        rv_checkout.layoutManager = LinearLayoutManager(this)
        rv_checkout.adapter = CheckOutAdapter(dataList){

        }

        val local = Locale("en","SG")
        val formatSGD = NumberFormat.getCurrencyInstance(local)
        tv_balance.setText(formatSGD.format(preferences.getValue("balance")!!.toDouble()))

        btn_purchase.setOnClickListener {
            startActivity(Intent(this@CheckOutActivity, CheckOutSuccess::class.java))
        }

        btn_cancel.setOnClickListener {
            finishAffinity()
            startActivity(Intent(this@CheckOutActivity, HomeActivity::class.java))
        }

        back.setOnClickListener {
            onBackPressed()
        }
    }
}