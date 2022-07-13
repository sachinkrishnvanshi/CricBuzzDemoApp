package com.example.codelab5.ui

import android.os.Bundle
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.codelab5.R
import com.example.codelab5.`interface`.DetailsInterface
import com.example.codelab5.adapter.RecyclerAdapter
import com.example.codelab5.databinding.FragmentHomeBinding
import com.example.codelab5.model.PlayerEntity
import com.example.codelab5.model.PlayerViewModel
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    val arrayList = ArrayList<PlayerEntity>()
    var binding: FragmentHomeBinding? = null
    lateinit var playerViewModel: PlayerViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding= FragmentHomeBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_floating.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_addPlayerDetailsFragment)
        }

        binding?.etSearch?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0!!.length>=3){
                    playerViewModel.readAllData.observe(requireActivity()){it->
                        val list=ArrayList<PlayerEntity>()
                        it.forEach {
                            if (it.name.contains(p0,true)){
                                list.add(it)
                                if (list.size>0){
                                    val adapter1=RecyclerAdapter(this@HomeFragment)
                                    binding?.recyclerHome?.adapter=adapter1
                                    binding?.tvNoData?.visibility=View.INVISIBLE
                                    adapter1.setData(list)

                                }

                            }
                            else if (list.isEmpty()){

                                binding?.tvNoData?.visibility=View.VISIBLE
                                binding?.recyclerHome?.adapter=null

                            }
                        }
                    }
                }
                else{
                    playerViewModel.readAllData.observe(requireActivity()){it->
                        val list=ArrayList<PlayerEntity>()
                        it.forEach {

                            list.add(it)
                        }
                        if (list.size>0) {
                            if (isAdded) {
                                val adapter1=RecyclerAdapter(this@HomeFragment)
                                binding?.recyclerHome?.adapter = adapter1
                                binding?.tvNoData?.visibility=View.INVISIBLE
                                adapter1.setData(list)

                            }
                        }



                    }
                }
                return false

            }

        })

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding?.recyclerHome?.layoutManager= LinearLayoutManager(requireContext())
        val adapter= RecyclerAdapter(this@HomeFragment)
        binding?.recyclerHome?.adapter=adapter
        playerViewModel= ViewModelProvider(this).get(PlayerViewModel::class.java)
        playerViewModel.readAllData.observe(viewLifecycleOwner, Observer { player ->
            adapter.setData(player)
        })

        nav_drawer.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.menu_home ->{
                    findNavController().navigate(R.id.homeFragment)
                }

               R.id.batsman -> {
                   findNavController().navigate(R.id.action_homeFragment_to_batsmanFragment)
               }
                R.id.bowler -> {
                    findNavController().navigate(R.id.action_homeFragment_to_bowlerFragment)
                }
                R.id.favourite -> {
                    findNavController().navigate(R.id.action_homeFragment_to_favouriteFragment)
                }
            };true
        }

        binding?.ivMenu?.setOnClickListener {
         binding?.drawer?.openDrawer(Gravity.LEFT)
        }

        }

    }

