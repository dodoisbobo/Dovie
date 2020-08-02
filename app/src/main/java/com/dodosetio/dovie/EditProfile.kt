package com.dodosetio.dovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dodosetio.dovie.util.Preferences
import kotlinx.android.synthetic.main.activity_edit_profile.*
import kotlinx.android.synthetic.main.fragment_profile.*
import kotlinx.android.synthetic.main.fragment_profile.profile_pic
import kotlinx.android.synthetic.main.fragment_profile.tv_name

class EditProfile : AppCompatActivity() {
    lateinit var preferences: Preferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        preferences= Preferences(this)

        tv_username.text = preferences.getValue("username")
        tv_password.text = preferences.getValue("password")
        tv_name.text = preferences.getValue("name")
        tv_user_email.text = preferences.getValue("email")
        Glide.with(this)
            .load(preferences.getValue("url"))
            .apply(RequestOptions.circleCropTransform())
            .into(profile_pic)

        back.setOnClickListener {
            onBackPressed()
        }
    }
}