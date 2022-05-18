package com.example.easycashchallenge.ui.main.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.easycashchallenge.R
import com.example.easycashchallenge.databinding.CompitionItemLayoutBinding
import com.example.easycashchallenge.network.models.Competition

class CompetitionAdapter(
    private val context: Context,
    private val onItemClickedListener: OnItemClickedListener
) :
    RecyclerView.Adapter<CompetitionAdapter.CompetitionHolder>() {

    private var competitions = ArrayList<Competition>()

    inner class CompetitionHolder(private val binding: CompitionItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(competition: Competition) {
            with(binding) {

                competition.emblemUrl?.let {  url ->

                    if (url.endsWith(".png")) {
                        Glide.with(context).load(url)
                            .placeholder(R.drawable.ic_loading)
                            .error(R.drawable.ic_launcher_background)
                            .fitCenter()
                            .into(ivEmblem)
                    } else {
                        val imageLoader = ImageLoader.Builder(context)
                            .componentRegistry {
                                add(SvgDecoder(context))
                            }.build()
                        val request = ImageRequest.Builder(context).apply {
                            placeholder(R.drawable.ic_loading)
                            error(R.drawable.ic_launcher_background)
                            data(url).decoder(SvgDecoder(context))
                        }.target(ivEmblem).build()
                        imageLoader.enqueue(request)
                    }
                }

                tvCompetitionName.text = competition.name
                tvCompetitionCode.text = competition.code
                tvSeasons.text = competition.numberOfAvailableSeasons.toString()
            }
        }

        init {
            itemView.setOnClickListener {
                onItemClickedListener.onItemClicked(competitions[adapterPosition])
            }
        }
    }

    fun setCompetitions(competitions: ArrayList<Competition>) {
        this.competitions = competitions
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompetitionHolder {
        val binding =
            CompitionItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return CompetitionHolder(binding)
    }

    override fun onBindViewHolder(holder: CompetitionHolder, position: Int) =
        holder.bind(competitions[position])

    override fun getItemCount(): Int = competitions.size
}