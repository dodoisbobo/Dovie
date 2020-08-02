package com.dodosetio.dovie.checkout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.dodosetio.dovie.R
import com.dodosetio.dovie.model.CheckOut
import com.dodosetio.dovie.model.Film
import kotlinx.android.synthetic.main.activity_choose_seat.*

class ChooseSeat : AppCompatActivity() {

    var statusA1:Boolean =false
    var statusA2:Boolean =false
    var statusA3:Boolean =false
    var statusA4:Boolean =false
    var statusB1:Boolean =false
    var statusB2:Boolean =false
    var statusB3:Boolean =false
    var statusB4:Boolean =false
    var statusC1:Boolean =false
    var statusC2:Boolean =false
    var statusC3:Boolean =false
    var statusC4:Boolean =false
    var statusD1:Boolean =false
    var statusD2:Boolean =false
    var statusD3:Boolean =false
    var statusD4:Boolean =false
    var total:Int=0

    private var dataList = ArrayList<CheckOut>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_seat)

        val data = intent.getParcelableExtra<Film>("data")
        tv_chair.text = data!!.judul

        a1.setOnClickListener {
            val data = CheckOut("A1", "6")
            if(statusA1){
                a1.setImageResource(R.drawable.empty)
                statusA1 = false
                total -= 1
                buyTicket(total)
                dataList.remove(data)
            }else{
                a1.setImageResource(R.drawable.selected)
                statusA1 = true
                total += 1
                buyTicket(total)
                dataList.add(data)
            }
        }
        a2.setOnClickListener {
            val data = CheckOut("A2", "6")
            if(statusA2){
                a2.setImageResource(R.drawable.empty)
                statusA2 = false
                total -= 1
                buyTicket(total)
                dataList.remove(data)
            }else{
                a2.setImageResource(R.drawable.selected)
                statusA2 = true
                total += 1
                buyTicket(total)
                dataList.add(data)
            }
        }

        a3.setOnClickListener {
            val data = CheckOut("A3", "6")
            if(statusA3){
                a3.setImageResource(R.drawable.empty)
                statusA3 = false
                total -= 1
                buyTicket(total)
                dataList.remove(data)
            }else{
                a3.setImageResource(R.drawable.selected)
                statusA3 = true
                total += 1
                buyTicket(total)
                dataList.add(data)
            }
        }
        a4.setOnClickListener {
            val data = CheckOut("A4", "6")
            if(statusA4){
                a4.setImageResource(R.drawable.empty)
                statusA4 = false
                total -= 1
                buyTicket(total)
                dataList.remove(data)
            }else{
                a4.setImageResource(R.drawable.selected)
                statusA4 = true
                total += 1
                buyTicket(total)
                dataList.add(data)
            }
        }

        b1.setOnClickListener {
            val data = CheckOut("B1", "6")
            if(statusB1){
                b1.setImageResource(R.drawable.empty)
                statusB1 = false
                total -= 1
                buyTicket(total)
                dataList.remove(data)
            }else{
                b1.setImageResource(R.drawable.selected)
                statusB1 = true
                total += 1
                buyTicket(total)
                dataList.add(data)
            }
        }
        b2.setOnClickListener {
            val data = CheckOut("B2", "6")
            if(statusB2){
                b2.setImageResource(R.drawable.empty)
                statusB2 = false
                total -= 1
                buyTicket(total)
                dataList.remove(data)
            }else{
                b2.setImageResource(R.drawable.selected)
                statusB2 = true
                total += 1
                buyTicket(total)
                dataList.add(data)
            }
        }

        b3.setOnClickListener {
            val data = CheckOut("B3", "6")
            if(statusB3){
                b3.setImageResource(R.drawable.empty)
                statusB3 = false
                total -= 1
                buyTicket(total)
                dataList.remove(data)
            }else{
                b3.setImageResource(R.drawable.selected)
                statusB3 = true
                total += 1
                buyTicket(total)
                dataList.add(data)
            }
        }
        b4.setOnClickListener {
            val data = CheckOut("B4", "6")
            if(statusB4){
                b4.setImageResource(R.drawable.empty)
                statusB4 = false
                total -= 1
                buyTicket(total)
                dataList.remove(data)
            }else{
                b4.setImageResource(R.drawable.selected)
                statusB4 = true
                total += 1
                buyTicket(total)
                dataList.add(data)
            }
        }

        c1.setOnClickListener {
            val data = CheckOut("C1", "6")
            if(statusC1){
                c1.setImageResource(R.drawable.empty)
                statusC1 = false
                total -= 1
                buyTicket(total)
                dataList.remove(data)
            }else{
                c1.setImageResource(R.drawable.selected)
                statusC1 = true
                total += 1
                buyTicket(total)
                dataList.add(data)
            }
        }
        c2.setOnClickListener {
            val data = CheckOut("C2", "6")
            if(statusC2){
                c2.setImageResource(R.drawable.empty)
                statusC2 = false
                total -= 1
                buyTicket(total)
                dataList.remove(data)
            }else{
                c2.setImageResource(R.drawable.selected)
                statusC2 = true
                total += 1
                buyTicket(total)
                dataList.add(data)
            }
        }

        c3.setOnClickListener {
            val data = CheckOut("C3", "6")
            if(statusC3){
                c3.setImageResource(R.drawable.empty)
                statusC3 = false
                total -= 1
                buyTicket(total)
                dataList.remove(data)
            }else{
                c3.setImageResource(R.drawable.selected)
                statusC3 = true
                total += 1
                buyTicket(total)
                dataList.add(data)
            }
        }
        c4.setOnClickListener {
            val data = CheckOut("C4", "6")
            if(statusC4){
                c4.setImageResource(R.drawable.empty)
                statusC4 = false
                total -= 1
                buyTicket(total)
                dataList.remove(data)
            }else{
                c4.setImageResource(R.drawable.selected)
                statusC4 = true
                total += 1
                buyTicket(total)
                dataList.add(data)
            }
        }

        d1.setOnClickListener {
            val data = CheckOut("D1", "6")
            if(statusD1){
                d1.setImageResource(R.drawable.empty)
                statusD1 = false
                total -= 1
                buyTicket(total)
                dataList.remove(data)
            }else{
                d1.setImageResource(R.drawable.selected)
                statusD1 = true
                total += 1
                buyTicket(total)
                dataList.add(data)
            }
        }
        d2.setOnClickListener {
            val data = CheckOut("D2", "6")
            if(statusD2){
                d2.setImageResource(R.drawable.empty)
                statusD2 = false
                total -= 1
                buyTicket(total)
                dataList.remove(data)
            }else{
                d2.setImageResource(R.drawable.selected)
                statusD2 = true
                total += 1
                buyTicket(total)
                dataList.add(data)
            }
        }

        d3.setOnClickListener {
            val data = CheckOut("D3", "6")
            if(statusD3){
                d3.setImageResource(R.drawable.empty)
                statusD3 = false
                total -= 1
                buyTicket(total)
                dataList.remove(data)
            }else{
                d3.setImageResource(R.drawable.selected)
                statusD3 = true
                total += 1
                buyTicket(total)
                dataList.add(data)
            }
        }
        d4.setOnClickListener {
            val data = CheckOut("D4", "6")
            if(statusD4){
                d4.setImageResource(R.drawable.empty)
                statusD4 = false
                total -= 1
                buyTicket(total)
                dataList.remove(data)
            }else{
                d4.setImageResource(R.drawable.selected)
                statusD4 = true
                total += 1
                buyTicket(total)
                dataList.add(data)
            }
        }

        btn_purchase.setOnClickListener {
            startActivity(Intent(this@ChooseSeat,
                CheckOutActivity::class.java).putExtra("data",dataList))
        }

        back.setOnClickListener {
            onBackPressed()
        }

    }

    private fun buyTicket(total: Int) {
        if(total == 0) {
            btn_purchase.visibility = View.INVISIBLE
        }else{
            btn_purchase.setText("Purchase ("+ total+")")
            btn_purchase.visibility = View.VISIBLE
        }
    }
}