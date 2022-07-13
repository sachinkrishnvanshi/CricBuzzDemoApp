package com.example.codelab5.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codelab5.R
import com.example.codelab5.adapter.BatsmanAdapter
import com.example.codelab5.adapter.FavouriteAdapter
import com.example.codelab5.databinding.FragmentBatsmanBinding
import com.example.codelab5.databinding.FragmentFavouriteBinding
import com.example.codelab5.model.PlayerEntity
import com.example.codelab5.model.PlayerViewModel
import kotlinx.android.synthetic.main.fragment_batsman.*
import kotlinx.android.synthetic.main.fragment_favourite.*

class FavouriteFragment : Fragment() {

    private lateinit var playerViewModel: PlayerViewModel
    private var _binding: FragmentFavouriteBinding? = null
    val playerList=ArrayList<PlayerEntity>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        playerViewModel= ViewModelProvider(this).get(PlayerViewModel::class.java)

        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_fav.layoutManager= LinearLayoutManager(requireContext())


        playerViewModel.readAllData.observe(viewLifecycleOwner, Observer { player->
            playerList.addAll(player)
            val arrayList = ArrayList<PlayerEntity>()


            player.forEach {
                if (it.favourite == true){
                    arrayList.add(it)
                }
            }
            val adapter= FavouriteAdapter(arrayList,requireActivity())

            recycler_fav.adapter = adapter
            adapter.notifyDataSetChanged()
            adapter.setData(arrayList)

        })
        binding.ivFavBack.setOnClickListener {
            findNavController().navigate(R.id.action_favouriteFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}