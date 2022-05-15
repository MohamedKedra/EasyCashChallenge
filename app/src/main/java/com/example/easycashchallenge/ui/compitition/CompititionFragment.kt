package com.example.easycashchallenge.ui.compitition

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.easycashchallenge.databinding.CompititionFragmentBinding

class CompititionFragment : Fragment() {

    private lateinit var binding: CompititionFragmentBinding
    private lateinit var adapter: TeamAdapter

    companion object {
        fun newInstance() = CompititionFragment()
    }

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
            adapter = TeamAdapter(requireContext())
            rvTeams.adapter = adapter
        }
    }

}