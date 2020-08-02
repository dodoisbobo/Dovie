package com.dodosetio.dovie.home.ticket

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dodosetio.dovie.R
import com.dodosetio.dovie.home.dashboard.ComingSoonAdapter
import com.dodosetio.dovie.model.Film
import com.dodosetio.dovie.util.Preferences
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_ticket.*


class TicketFragment : Fragment() {

    private lateinit var preferences: Preferences
    private lateinit var mDatabase:DatabaseReference
    private var dataList=ArrayList<Film>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ticket, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences = Preferences(context!!)
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        rv_ticket.layoutManager = LinearLayoutManager(context)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object:ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                Toast.makeText(context,p0.message,Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(p0: DataSnapshot) {
                dataList.clear()
                for (getDataSnapshot in p0.children){
                    var film = getDataSnapshot.getValue(Film::class.java)
                    dataList.add(film!!)
                }
                rv_ticket.adapter = ComingSoonAdapter(dataList){
                    var intent = Intent(context, TicketDetail::class.java).putExtra("data",it)
                    startActivity(intent)
                }

                tv_total.setText("${dataList.size} Movies")

            }

        })
    }


}