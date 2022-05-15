package com.example.easycashchallenge.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.easycashchallenge.R
import com.example.easycashchallenge.base.BaseFragment
import com.example.easycashchallenge.databinding.MainFragmentBinding

class MainFragment : BaseFragment(), OnItemClickedListener {

    private lateinit var binding: MainFragmentBinding
    private lateinit var competitionAdapter: CompetitionAdapter

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            MainFragmentBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        return binding.root
    }

    override val layoutId: View
        get() = binding.root

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        with(binding) {
            competitionAdapter = CompetitionAdapter(requireContext(), this@MainFragment)
            rvCompetition.adapter = competitionAdapter
        }
    }

    override fun onItemClicked() {
        navigationController.navigate(R.id.action_HomeFragment_to_CompetitionFragment)
    }
}