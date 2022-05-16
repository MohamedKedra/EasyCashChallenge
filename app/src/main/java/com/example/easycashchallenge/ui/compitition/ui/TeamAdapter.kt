package com.example.easycashchallenge.ui.compitition.ui

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.easycashchallenge.R
import com.example.easycashchallenge.databinding.TeamItemLayoutBinding
import com.example.easycashchallenge.network.models.Team
import com.example.easycashchallenge.ui.team.OnTeamSelectedListener

class TeamAdapter(
    private val context: Context,
    private val onTeamSelectedListener: OnTeamSelectedListener
) :
    RecyclerView.Adapter<TeamAdapter.TeamViewHolder>() {

    private var teams = ArrayList<Team>()

    inner class TeamViewHolder(private val binding: TeamItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(team: Team) {
            with(binding) {
                team.crestUrl?.let { url ->

                    if (url.endsWith(".png")) {
                        Glide.with(context).load(url).placeholder(R.drawable.ic_default)
                            .into(ivTeamFlag)
                    } else {

                    }
                }
                tvTeamName.text = team.name
                tvTeamCode.text = team.tla
            }
        }

        init {
            itemView.setOnClickListener {
                onTeamSelectedListener.onTeamSelected(teams[adapterPosition])
            }
        }
    }

    fun setTeams(teams: ArrayList<Team>) {
        this.teams = teams
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val binding = TeamItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return TeamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) =
        holder.bind(teams[position])

    override fun getItemCount(): Int = teams.size
}