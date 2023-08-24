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
import com.example.premierleaguefootball.data.model.Team
import com.example.premierleaguefootball.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {

    private val viewmodel: TeamViewModel by activityViewModels()
    private lateinit var binding: FragmentDetailBinding

    private lateinit var team: Team




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel.loadTeams()


        arguments?.let {
            val index = it.getInt("position")

            team = viewmodel.teams.value!![index]

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

        viewmodel.teams.observe(viewLifecycleOwner){
            binding.speciesTV.text = team.species
            binding.houseTV.text = team.house
            binding.ancestoryTV.text = team.ancestry
            binding.actorTV.text = team.actor
            binding.charactertv.setText(team.name)
            val imgUri = team.image.toUri().buildUpon().scheme("https").build()
            binding.cityPicIV.load(imgUri) {
                error(R.drawable.images)
                transformations(RoundedCornersTransformation(10F))
            }

            binding.homeBTN.setOnClickListener {
                findNavController().navigateUp()
            }

        }





    }

}