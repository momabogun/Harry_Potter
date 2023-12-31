package com.example.premierleaguefootball.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.premierleaguefootball.R
import com.example.premierleaguefootball.data.model.Character
import com.example.premierleaguefootball.databinding.ListItemBinding
import com.example.premierleaguefootball.ui.HomeFragmentDirections

class TeamAdapter(
    private var dataset: List<Character>
) : RecyclerView.Adapter<TeamAdapter.ItemViewHolder>() {


    inner class ItemViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root)


    @SuppressLint("NotifyDataSetChanged")
    fun newData(newList: List<Character>){
        dataset = newList
        notifyDataSetChanged()

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ListItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]
        holder.binding.teamTV.text = item.name
        val imgUri = item.image.toUri().buildUpon().scheme("https").build()
        holder.binding.flagIV.load(imgUri) {
            error(R.drawable.images)
            transformations(RoundedCornersTransformation(10F))
        }
        holder.itemView.setOnClickListener {
            val navControler = holder.itemView.findNavController()
            navControler.navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(item.id))

        }






    }

    override fun getItemCount(): Int {
        return dataset.size
    }
}