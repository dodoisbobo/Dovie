package com.dodosetio.dovie.home.ticket

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dodosetio.dovie.R
import com.dodosetio.dovie.model.CheckOut
import com.dodosetio.dovie.model.Film
import com.dodosetio.dovie.moviedetail.MovieDetailActivity
import kotlinx.android.synthetic.main.activity_scan_qr.*
import kotlinx.android.synthetic.main.activity_ticket_detail.*
import kotlinx.android.synthetic.main.activity_ticket_detail.poster_image
import kotlinx.android.synthetic.main.activity_ticket_detail.rv_seats
import kotlinx.android.synthetic.main.activity_ticket_detail.tv_genre
import kotlinx.android.synthetic.main.activity_ticket_detail.tv_rating
import kotlinx.android.synthetic.main.activity_ticket_detail.tv_title

class ScanQR : AppCompatActivity() {

    private var dataList = ArrayList<CheckOut>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scan_qr)

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

        close.setOnClickListener {
            var intent = Intent(this@ScanQR, TicketDetail::class.java).putExtra("data",data)
            startActivity(intent)
        }
    }
}