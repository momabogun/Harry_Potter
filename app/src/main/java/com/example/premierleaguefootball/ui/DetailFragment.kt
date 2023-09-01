package com.example.premierleaguefootball.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.premierleaguefootball.R
import com.example.premierleaguefootball.data.model.Character
import com.example.premierleaguefootball.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val viewmodel: CharViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailBinding

    private var contactId: String = ""




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let { it ->
            contactId = it.getString("charId","")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewmodel.getChar(contactId)

            binding.speciesTV.text = character.species
            binding.houseTV.text = character.house
            binding.ancestoryTV.text = character.ancestry
            binding.actorTV.text = character.actor
        binding.charactertv.text = character.name
            val imgUri = character.image.toUri().buildUpon().scheme("https").build()
            binding.cityPicIV.load(imgUri) {
                error(R.drawable.images)
                transformations(RoundedCornersTransformation(10F))
            }

            binding.homeBTN.setOnClickListener {
                findNavController().navigateUp()
            }

        }






}