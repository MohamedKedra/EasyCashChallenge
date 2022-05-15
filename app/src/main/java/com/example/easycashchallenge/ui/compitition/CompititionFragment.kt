package com.example.easycashchallenge.ui.compitition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.easycashchallenge.R
import com.example.easycashchallenge.base.BaseFragment
import com.example.easycashchallenge.databinding.CompititionFragmentBinding
import com.example.easycashchallenge.ui.team.OnTeamSelectedListener

class CompititionFragment(override val layoutId: View) : BaseFragment(), OnTeamSelectedListener {

    private lateinit var binding: CompititionFragmentBinding
    private lateinit var adapter: TeamAdapter

    private lateinit var viewModel: CompititionViewModel

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CompititionViewModel::class.java)

        with(binding) {
            adapter = TeamAdapter(requireContext(), this@CompititionFragment)
            rvTeams.adapter = adapter
        }
    }

    override fun onTeamSelected() {
        navigationController.navigate(R.id.action_CompetitionFragment_to_TeamFragment)
    }
}