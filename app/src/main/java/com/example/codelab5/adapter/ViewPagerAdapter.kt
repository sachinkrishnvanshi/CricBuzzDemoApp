package com.example.codelab5.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.codelab5.model.PlayerEntity
import com.example.codelab5.ui.info.BattingInfoFragment
import com.example.codelab5.ui.info.BowlingInfoFragment
import com.example.codelab5.ui.info.InfoFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity, val playerEntity: PlayerEntity):FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
       return 3
    }

    override fun createFragment(position: Int): Fragment {
       return when(position){
           0 -> InfoFragment(playerEntity)
           1 -> BattingInfoFragment(playerEntity)
           2 -> BowlingInfoFragment(playerEntity)
           else -> {
               Fragment()
           }
       }
    }

}