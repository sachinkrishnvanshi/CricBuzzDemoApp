package com.example.codelab5.ui.info

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.codelab5.R
import com.example.codelab5.databinding.FragmentBattingInfoBinding
import com.example.codelab5.databinding.FragmentBowlingInfoBinding
import com.example.codelab5.model.PlayerEntity


class BowlingInfoFragment(val playerEntity: PlayerEntity) : Fragment() {
    var binding: FragmentBowlingInfoBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentBowlingInfoBinding.inflate(inflater, container, false)
        binding?.tvBowlingMatch?.text=playerEntity.match
        binding?.tvBowlingWicket?.text=playerEntity.wicket

        return binding!!.root
    }

}