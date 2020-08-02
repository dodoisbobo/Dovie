package com.dodosetio.dovie.home.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dodosetio.dovie.moviedetail.MovieDetailActivity
import com.dodosetio.dovie.R
import com.dodosetio.dovie.model.Film
import com.dodosetio.dovie.sign_in_out.signin.User
import com.dodosetio.dovie.util.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class DashboardFragment : Fragment() {
    private lateinit var preferences: Preferences
    private lateinit var user:User
    private lateinit var mDatabase:DatabaseReference

    private var dataList = ArrayList<Film>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(activity!!.applicationContext)
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")
        name.setText(preferences.getValue("name"))
//        if(preferences.getValue("balance").equals("")){
        currency(preferences.getValue("balance")!!.toDouble(),balance)
//        }
        if(preferences.getValue("url").equals("")){

        }else{
            Glide.with(this)
                .load(preferences.getValue("url").toString())
                .apply(RequestOptions.circleCropTransform())
                .into(profile_pic)
        }
        now_playing.layoutManager = LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL, false)
        coming_soon.layoutManager = LinearLayoutManager(context)
        getData()


    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context,databaseError.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {

                dataList.clear()
                for (getdataSnapshot in dataSnapshot.children){
                    var film = getdataSnapshot.getValue(Film::class.java)

                    dataList.add(film!!)

                }
                now_playing.adapter = NowPlayingAdapter(dataList){
                    var intent = Intent(context, MovieDetailActivity::class.java).putExtra("data",it)
                    startActivity(intent)
                }
                coming_soon.adapter = ComingSoonAdapter(dataList){
                    var intent = Intent(context, MovieDetailActivity::class.java).putExtra("data",it)
                    startActivity(intent)
                }
            }

        })
    }

    private fun currency(price:Double, textView: TextView){
        val localSgd = Locale("en","SG")
        val format = NumberFormat.getCurrencyInstance(localSgd)
        textView.setText(format.format(price))
    }
}