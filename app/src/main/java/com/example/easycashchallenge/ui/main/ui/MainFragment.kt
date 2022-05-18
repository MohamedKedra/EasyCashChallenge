package com.example.easycashchallenge.ui.main.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.easycashchallenge.R
import com.example.easycashchallenge.base.BaseFragment
import com.example.easycashchallenge.base.DataState
import com.example.easycashchallenge.databinding.MainFragmentBinding
import com.example.easycashchallenge.network.models.Competition
import com.example.easycashchallenge.ui.main.viewmodel.MainViewModel
import com.example.easycashchallenge.utils.Constants
import kotlinx.android.synthetic.main.error_list_layout.*
import org.koin.android.ext.android.inject

class MainFragment : BaseFragment(), OnItemClickedListener {

    private lateinit var binding: MainFragmentBinding
    private lateinit var competitionAdapter: CompetitionAdapter
    private val viewModel by inject<MainViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            MainFragmentBinding.inflate(LayoutInflater.from(requireContext()), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            observeCompetitions()
            competitionAdapter = CompetitionAdapter(requireContext(), this@MainFragment)
            rvCompetition.adapter = competitionAdapter

            btnIssue.setOnClickListener {
                observeCompetitions()
            }
        }
    }

    private fun observeCompetitions() {
        viewModel.refreshMain().observe(viewLifecycleOwner) {
            when (it.getStatus()) {
                DataState.DataStatus.LOADING -> {
                    showOrHideLoading(isLoading = true)
                }
                DataState.DataStatus.SUCCESS -> {
                    showOrHideLoading()
                    it.getData()?.competitions?.let { list ->
                        competitionAdapter.setCompetitions(list as ArrayList<Competition>)
                        competitionAdapter.notifyDataSetChanged()
                    }
                }
                DataState.DataStatus.ERROR -> {
                    showOrHideLoading(hasIssue = true, txt = getString(R.string.error))
                }
                DataState.DataStatus.NO_INTERNET -> {
                    showOrHideLoading(hasIssue = true, txt = getString(R.string.no_internet))
                }
            }
        }
    }

    override fun onItemClicked(competition: Competition) {
        val bundle = Bundle()
        bundle.putParcelable(Constants.Const.Competition, competition)
        navigationController.navigate(R.id.action_HomeFragment_to_CompetitionFragment, bundle)
    }
}