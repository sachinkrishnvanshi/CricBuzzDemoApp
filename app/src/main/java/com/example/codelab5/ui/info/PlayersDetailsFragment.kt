package com.example.codelab5.ui.info

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.codelab5.R
import com.example.codelab5.adapter.ViewPagerAdapter
import com.example.codelab5.databinding.FragmentPlayersDetailsBinding
import com.example.codelab5.model.PlayerEntity
import com.google.android.material.tabs.TabLayoutMediator


class PlayersDetailsFragment(val playerEntity: PlayerEntity) : Fragment() {

    private lateinit var binding: FragmentPlayersDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPlayersDetailsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter=ViewPagerAdapter(requireActivity(),playerEntity)
        TabLayoutMediator(binding.tabLayout,binding.viewPager){
            tab,position -> tab.text = when(position){
                0 -> {"INFO"}
                1 -> {"BATTING"}
                2  -> {"BOWLING"}
                else -> {throw  Resources.NotFoundException("Position Not Found")}
            }
        }.attach()
        binding.tvDetailName.text=playerEntity.name

//        binding.ivBack.setOnClickListener {
//            findNavController().navigate(R.id.action_playersDetailsFragment_to_homeFragment)
//        }
    }



}