package com.example.codelab5.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codelab5.R
import com.example.codelab5.model.PlayerEntity
import kotlinx.android.synthetic.main.batsman_card_layout.view.*
import kotlinx.android.synthetic.main.bowler_card_layout.view.*

class BowlerAdapter (private var playerList: List<PlayerEntity>, private val context: Context):
    RecyclerView.Adapter<BowlerAdapter.ViewHolder>() {


    inner class ViewHolder (view : View) : RecyclerView.ViewHolder(view) {

    }

    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
// Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.bowler_card_layout, parent, false)

        return ViewHolder(view)

    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        var info = playerList[position]

        viewHolder.itemView.iv_player_bowler.setImageBitmap(BitmapFactory.decodeByteArray(info.image, 0, info.image!!.size))
        viewHolder.itemView.tv_player_bowler.text= info.name
        viewHolder.itemView.tv_country_bowler.text = info.country


    }


    override fun getItemCount() :Int{
        return playerList.size
    }
    @SuppressLint("NotifyDataSetChanged")
    fun setData(user:List<PlayerEntity>) {
        this.playerList=user
        notifyDataSetChanged()
    }

}