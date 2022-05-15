package com.example.easycashchallenge.ui.compitition

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easycashchallenge.databinding.TeamItemLayoutBinding

class TeamAdapter(private val context: Context) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {


    inner class TeamViewHolder(private val binding: TeamItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = TeamItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 10
}