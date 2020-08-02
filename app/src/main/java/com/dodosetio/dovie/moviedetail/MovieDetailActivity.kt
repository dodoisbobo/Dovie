package com.dodosetio.dovie.moviedetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dodosetio.dovie.R
import com.dodosetio.dovie.checkout.ChooseSeat
import com.dodosetio.dovie.home.HomeActivity
import com.dodosetio.dovie.home.dashboard.PlaysAdapter
import com.dodosetio.dovie.model.Film
import com.dodosetio.dovie.model.Plays
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_choose_seat.*
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.back
import kotlinx.android.synthetic.main.activity_movie_detail.tv_chair

class MovieDetailActivity : AppCompatActivity() {

    private lateinit var mDatabase:DatabaseReference
    private  var dataList = ArrayList<Plays>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        val data = intent.getParcelableExtra<Film>("data")
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
            .child(data!!.judul.toString())
            .child("play")

        tv_chair.text = data!!.judul
        tv_genre.text = data!!.genre
        tv_description.text = data!!.desc
        tv_rate.text = data!!.rating

        Glide.with(this)
            .load(data.poster)
            .into(poster)

        whoplay.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false)
        getData()

        chooseSeat.setOnClickListener {
            startActivity(Intent(this@MovieDetailActivity,
                ChooseSeat::class.java).putExtra("data",data))
        }
        back.setOnClickListener {
            onBackPressed()
        }
    }

    private fun getData() {
        mDatabase.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(this@MovieDetailActivity,p0.message,Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                dataList.clear()

                for(getDataSnapshot in p0.children){
                    var Film = getDataSnapshot.getValue(Plays::class.java)
                    dataList.add(Film!!)
                }

                whoplay.adapter = PlaysAdapter(dataList){

                }
            }

        })
    }
}