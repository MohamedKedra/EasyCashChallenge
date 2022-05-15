package com.example.easycashchallenge.ui.compitition

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easycashchallenge.databinding.TeamItemLayoutBinding
import com.example.easycashchallenge.ui.team.OnTeamSelectedListener

class TeamAdapter(private val context: Context,private val onTeamSelectedListener: OnTeamSelectedListener) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {


    inner class TeamViewHolder(private val binding: TeamItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root){
            init {
                onTeamSelectedListener.onTeamSelected()
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = TeamItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {

    }

    override fun getItemCount(): Int = 10
}