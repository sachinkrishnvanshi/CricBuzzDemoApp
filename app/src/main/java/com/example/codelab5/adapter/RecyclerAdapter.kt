package com.example.codelab5.adapter

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.codelab5.R
import com.example.codelab5.databinding.PlayerCardLayoutBinding
import com.example.codelab5.model.PlayerEntity
import com.example.codelab5.ui.HomeFragment
import com.example.codelab5.ui.info.PlayersDetailsFragment
import kotlinx.android.synthetic.main.player_card_layout.view.*

class RecyclerAdapter(private val context: HomeFragment): RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>() {
    var binding:PlayerCardLayoutBinding? = null
    private var playerList = emptyList<PlayerEntity>()

    inner class MyViewHolder(val binding:PlayerCardLayoutBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        binding= PlayerCardLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MyViewHolder(binding!!)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val res=playerList[position]
//        holder.binding.ivPlayerName.setImageBitmap(res.image)
        holder.itemView.iv_player_name.setImageBitmap(BitmapFactory.decodeByteArray(res.image, 0, res.image!!.size))
        holder.binding.tvPlayerName.text=res.name
        holder.binding.tvCountryName.text=res.country


        holder.itemView.setOnClickListener {
        context.activity?.supportFragmentManager?.beginTransaction()?.addToBackStack(null)?.replace(R.id.drawer,PlayersDetailsFragment(playerList[position]))?.commit()
        }

    }

    override fun getItemCount(): Int {
        return playerList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(player: List<PlayerEntity>) {
        this.playerList = player
        notifyDataSetChanged()
    }
}