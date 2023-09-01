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


    private val viewModel: CharViewModel by viewModels()


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

        val adapter = TeamAdapter(emptyList())
        binding.footballRV.adapter = adapter


        viewModel.chars.observe(viewLifecycleOwner){
            adapter.newData(it)
        }

        binding.refreshBTN.setOnClickListener {
            viewModel.loadChars()
        }




    }
}