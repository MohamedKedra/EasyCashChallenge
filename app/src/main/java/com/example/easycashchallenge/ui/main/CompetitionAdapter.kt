package com.example.easycashchallenge.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.easycashchallenge.databinding.CompitionItemLayoutBinding

class CompetitionAdapter(
    private val context: Context,
    private val onItemClickedListener: OnItemClickedListener
) :
    RecyclerView.Adapter<CompetitionAdapter.CompetitionHolder>() {

    inner class CompetitionHolder(private val binding: CompitionItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                onItemClickedListener.onItemClicked()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionHolder {
        val binding =
            CompitionItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return CompetitionHolder(binding)
    }

    override fun onBindViewHolder(holder: CompetitionHolder, position: Int) {
    }

    override fun getItemCount(): Int = 10
}