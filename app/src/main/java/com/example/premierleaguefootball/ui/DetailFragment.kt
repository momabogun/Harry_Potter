package com.example.premierleaguefootball.ui

import android.app.AlertDialog
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
import com.example.premierleaguefootball.ui.DetailFragmentDirections

class DetailFragment : Fragment() {

    private val viewModel: CharViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailBinding

    private var charId: String = ""
    private lateinit var character: Character


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        arguments?.let { it ->
            charId = it.getString("charId", "")

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


        viewModel.getChar(charId).observe(viewLifecycleOwner) { character ->
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
            this.character = character
        }


        binding.homeBTN.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.deleteBTN.setOnClickListener {

            val builder = AlertDialog.Builder(requireContext())
            builder.setPositiveButton("Yes"){_, _ ->
                viewModel.deleteChar(character)
                findNavController().navigate(HomeFragmentD)

                // had problems with directions
            }
            builder.setNegativeButton("No"){ _, _ ->}
            builder.setTitle("Delete")
            builder.setMessage("Are you sure you want to delete $name?")
            builder.create().show()
        }

    }


}