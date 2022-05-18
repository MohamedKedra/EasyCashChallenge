package com.example.easycashchallenge.ui.compitition.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.example.easycashchallenge.R
import com.example.easycashchallenge.base.BaseFragment
import com.example.easycashchallenge.base.DataState
import com.example.easycashchallenge.databinding.CompititionFragmentBinding
import com.example.easycashchallenge.network.models.Competition
import com.example.easycashchallenge.network.models.Team
import com.example.easycashchallenge.ui.compitition.viewmodel.CompititionViewModel
import com.example.easycashchallenge.ui.team.OnTeamSelectedListener
import com.example.easycashchallenge.utils.Constants
import kotlinx.android.synthetic.main.compitition_fragment.*
import kotlinx.android.synthetic.main.error_list_layout.*
import org.koin.android.ext.android.inject

class CompititionFragment : BaseFragment(), OnTeamSelectedListener {

    private lateinit var binding: CompititionFragmentBinding
    private lateinit var adapter: TeamAdapter

    private val viewModel by inject<CompititionViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CompititionFragmentBinding.inflate(
            LayoutInflater.from(requireContext()),
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {

            observeTeams()
            adapter = TeamAdapter(requireContext(), this@CompititionFragment)
            rvTeams.adapter = adapter

            btnIssue.setOnClickListener {
                observeTeams()
            }
        }
    }

    private fun observeTeams() {
        val competition: Competition? = arguments?.getParcelable(Constants.Const.Competition)
        if (competition != null) {
            viewModel.getCachedTeams(id).observe(viewLifecycleOwner) {
                when (it.getStatus()) {
                    DataState.DataStatus.LOADING -> {
                        rvTeams.isVisible = false
                        compInfo.isVisible = false
                        showOrHideLoading(isLoading = true)
                    }
                    DataState.DataStatus.SUCCESS -> {
                        rvTeams.isVisible = true
                        compInfo.isVisible = true
                        showOrHideLoading()

                        tvCompetitionName.text = competition.name
                        Glide.with(requireContext()).load(competition.emblemUrl).into(ivEmblem)

                        it.getData()?.let { list ->
                            adapter.setTeams(list as ArrayList<Team>)
                            adapter.notifyDataSetChanged()
                        }
                    }
                    DataState.DataStatus.ERROR -> {
                        rvTeams.isVisible = false
                        compInfo.isVisible = false
                        showOrHideLoading(hasIssue = true, txt = getString(R.string.error_paid))
                    }
                    DataState.DataStatus.NO_INTERNET -> {
                        rvTeams.isVisible = false
                        compInfo.isVisible = false
                        showOrHideLoading(hasIssue = true, txt = getString(R.string.no_internet))
                    }
                }
            }
        }
    }

    override fun onTeamSelected(team: Team) {

        val bundle = Bundle()
        bundle.putParcelable(Constants.Const.Team,team)
        navigationController.navigate(R.id.action_CompetitionFragment_to_TeamFragment)
    }
}