package com.example.easycashchallenge.ui.team

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.easycashchallenge.base.BaseFragment
import com.example.easycashchallenge.databinding.TeamFragmentBinding

class TeamFragment(override val layoutId: View) : BaseFragment() {

    private lateinit var viewModel: TeamViewModel
    private lateinit var binding: TeamFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            TeamFragmentBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(TeamViewModel::class.java)

    }
}