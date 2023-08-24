package com.example.premierleaguefootball.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.premierleaguefootball.adapter.TeamAdapter
import com.example.premierleaguefootball.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {


    private val viewModel: TeamViewModel by viewModels()


    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = binding.footballRV


        viewModel.teams.observe(viewLifecycleOwner){
            recyclerView.adapter = TeamAdapter(it)
        }


        recyclerView.setHasFixedSize(true)

    }
}