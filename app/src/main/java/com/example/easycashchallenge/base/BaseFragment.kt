package com.example.easycashchallenge.base

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.error_list_layout.*

abstract class BaseFragment : Fragment() {

    protected lateinit var navigationController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigationController = findNavController()
    }

    protected fun showOrHideLoading(
        isLoading: Boolean = false,
        hasIssue: Boolean = false,
        txt: String = ""
    ) {
        pb_progressbar.isVisible = isLoading
        tvIssue.isVisible = hasIssue
        tvIssue.text = txt
        btnIssue.isVisible = hasIssue
    }
}