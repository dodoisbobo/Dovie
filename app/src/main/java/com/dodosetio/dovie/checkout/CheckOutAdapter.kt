package com.dodosetio.dovie.checkout

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dodosetio.dovie.R
import com.dodosetio.dovie.model.CheckOut
import java.text.NumberFormat
import java.util.*


class CheckOutAdapter(private var data: List<CheckOut>,
                      private val listener:(CheckOut) -> Unit) : RecyclerView.Adapter<CheckOutAdapter.ViewHolder>() {

    lateinit var contextAdapter: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CheckOutAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflatedView = layoutInflater.inflate(R.layout.row_item_checkout,parent,false)
        return ViewHolder(inflatedView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CheckOutAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position],listener, contextAdapter)
    }

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        private val tvSeat: TextView = view.findViewById(R.id.tv_chair)
        private val tvPrice: TextView = view.findViewById(R.id.tv_price)


        fun bindItem(data:CheckOut, listener: (CheckOut) -> Unit, context: Context){

            val local = Locale("en","SG")
            val formatSGD = NumberFormat.getCurrencyInstance(local)
            tvPrice.setText(formatSGD.format(data.harga!!.toDouble()))


            if(data.kursi!!.startsWith("Total")){
                tvSeat.setText(data.kursi)
                tvSeat.setCompoundDrawables(null,null,null,null)
            }else{
                tvSeat.setText("Seat No. "+data.kursi)
            }

            itemView.setOnClickListener {
                listener(data)
            }
        }
    }
}
