package com.dodosetio.dovie.home.ticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dodosetio.dovie.R
import com.dodosetio.dovie.home.HomeActivity
import com.dodosetio.dovie.model.CheckOut
import com.dodosetio.dovie.model.Film
import kotlinx.android.synthetic.main.activity_choose_seat.*
import kotlinx.android.synthetic.main.activity_ticket_detail.*
import kotlinx.android.synthetic.main.activity_ticket_detail.back

class TicketDetail : AppCompatActivity() {

    private var dataList = ArrayList<CheckOut>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_detail)

        var data = intent.getParcelableExtra<Film>("data")

        tv_title.text = data!!.judul
        tv_genre.text = data!!.genre
        tv_rating.text = data!!.rating

        Glide.with(this)
            .load(data.poster)
            .into(poster_image)

        rv_seats.layoutManager = LinearLayoutManager(this)
        dataList.add(CheckOut("C1",""))
        dataList.add(CheckOut("C2",""))

        rv_seats.adapter = TicketAdapter(dataList){

        }

        qr_code.setOnClickListener {
            var intent = Intent(this@TicketDetail, ScanQR::class.java).putExtra("data",data)
            startActivity(intent)
        }

        back.setOnClickListener {
            onBackPressed()
        }
    }
}