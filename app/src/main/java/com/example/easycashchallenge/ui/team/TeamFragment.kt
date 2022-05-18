package com.example.easycashchallenge.ui.team

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.easycashchallenge.base.BaseFragment
import com.example.easycashchallenge.databinding.TeamFragmentBinding
import com.example.easycashchallenge.network.models.Team
import com.example.easycashchallenge.utils.Constants

class TeamFragment : BaseFragment() {

    private lateinit var binding: TeamFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            TeamFragmentBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            val team = arguments?.getParcelable<Team>(Constants.Const.Team)
            Log.d("team",team.toString())
            team?.let {
                Glide.with(requireContext()).load(team.crestUrl).into(ivAvatar)
                tvTitle.text = team.name
                tvVenue.text = team.venue
                tvColor.text = team.clubColors
                tvEmail.text = team.email
                tvPhone.text = team.phone
                tvFounded.text = team.founded?.toString()

                btnWebsite.setOnClickListener {
                    val intent = Intent(Intent.ACTION_VIEW)
                    intent.data = Uri.parse(team.website)
                    startActivity(intent)
                }
            }
        }
    }
}