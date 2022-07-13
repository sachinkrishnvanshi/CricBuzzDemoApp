package com.example.codelab5.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codelab5.R
import com.example.codelab5.`interface`.DetailsInterface
import com.example.codelab5.adapter.BatsmanAdapter
import com.example.codelab5.adapter.RecyclerAdapter
import com.example.codelab5.databinding.FragmentBatsmanBinding
import com.example.codelab5.model.PlayerEntity
import com.example.codelab5.model.PlayerViewModel
import kotlinx.android.synthetic.main.fragment_batsman.*

class BatsmanFragment : Fragment() {
    private lateinit var playerViewModel: PlayerViewModel
    private var _binding: FragmentBatsmanBinding? = null
    val playerList=ArrayList<PlayerEntity>()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        playerViewModel= ViewModelProvider(this).get(PlayerViewModel::class.java)

        _binding = FragmentBatsmanBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_batsman.layoutManager= LinearLayoutManager(requireContext())


        playerViewModel.readAllData.observe(viewLifecycleOwner, Observer { player->
            playerList.addAll(player)
            val arrayList = ArrayList<PlayerEntity>()


            player.forEach {
                if (it.radioBatsman == true){
                    arrayList.add(it)
                }
            }
            val adapter= BatsmanAdapter(arrayList,requireActivity())

            recycler_batsman.adapter = adapter
            adapter.notifyDataSetChanged()
            adapter.setData(arrayList)

        })
        binding.ivBatBack.setOnClickListener {
            findNavController().navigate(R.id.action_batsmanFragment_to_homeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}