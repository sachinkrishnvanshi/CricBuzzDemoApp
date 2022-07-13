package com.example.codelab5.ui.info

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.codelab5.R
import com.example.codelab5.databinding.FragmentHomeBinding
import com.example.codelab5.databinding.FragmentInfoBinding
import com.example.codelab5.databinding.FragmentPlayersDetailsBinding
import com.example.codelab5.model.PlayerEntity
import kotlinx.android.synthetic.main.batsman_card_layout.view.*
import java.util.zip.Inflater


class InfoFragment(val playerEntity: PlayerEntity) : Fragment() {
    var binding: FragmentInfoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentInfoBinding.inflate(inflater, container, false)
        binding?.tvInfoName?.text=playerEntity.name
        binding?.tvInfoCountry?.text=playerEntity.country
        binding?.tvInfoTeam?.text=playerEntity.team
        binding?.tvInfoBorn?.text=playerEntity.dob
        binding?.ivInfoImage?.setImageBitmap(BitmapFactory.decodeByteArray(playerEntity.image, 0, playerEntity.image!!.size))

        return binding!!.root
    }


}