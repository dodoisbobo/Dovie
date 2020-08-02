package com.dodosetio.dovie.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.dodosetio.dovie.R
import com.dodosetio.dovie.home.dashboard.DashboardFragment
import com.dodosetio.dovie.home.profile.ProfileFragment
import com.dodosetio.dovie.home.ticket.TicketFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentHome = DashboardFragment()
        val fragmentTicket = TicketFragment()
        val fragmentProfile = ProfileFragment()

        setFragment(fragmentHome)

        menu1.setOnClickListener {
            setFragment(fragmentHome)
            changeIcon(menu1, R.drawable.menu_btn_active)
            changeIcon(menu2, R.drawable.tic_btn)
            changeIcon(menu3, R.drawable.profile_btn)
        }
        menu2.setOnClickListener {
            setFragment(fragmentTicket)
            changeIcon(menu1, R.drawable.ph_btn)
            changeIcon(menu2, R.drawable.tic_btn_active)
            changeIcon(menu3, R.drawable.profile_btn)
        }
        menu3.setOnClickListener {
            setFragment(fragmentProfile)
            changeIcon(menu1, R.drawable.ph_btn)
            changeIcon(menu2, R.drawable.tic_btn)
            changeIcon(menu3, R.drawable.profile_btn_active)
        }
    }
    private fun setFragment(fragment:Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.layout_frame, fragment)
        fragmentTransaction.commit()
    }

    private fun changeIcon(imageView: ImageView, int:Int){
        imageView.setImageResource(int)
    }
}