package com.dodosetio.dovie.home.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dodosetio.dovie.EditProfile
import com.dodosetio.dovie.R
import com.dodosetio.dovie.home.ticket.ScanQR
import com.dodosetio.dovie.util.Preferences
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    lateinit var preferences:Preferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preferences= Preferences(context!!)

        tv_name.text = preferences.getValue("name")
        tv_email.text = preferences.getValue("email")

        Glide.with(this)
            .load(preferences.getValue("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(profile_pic)
        tv_profile.setOnClickListener {
            var intent = Intent(context, EditProfile::class.java)
            startActivity(intent)

        }
    }
}