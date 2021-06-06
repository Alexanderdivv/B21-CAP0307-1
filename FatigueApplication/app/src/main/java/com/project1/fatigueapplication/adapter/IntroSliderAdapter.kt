package com.project1.fatigueapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project1.githubsub2.R
import com.project1.githubsub2.databinding.SlideItemContainerUserBinding
import com.project1.githubsub2.model.data.IntroSlide

class IntroSliderAdapter(private val introSlides: List<IntroSlide>) :
    RecyclerView.Adapter<IntroSliderAdapter.IntoSlideViewHolder>() {

    inner class IntoSlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = SlideItemContainerUserBinding.bind(view)

        private var textTitle = binding.tvTitleSlideIntro
        private var textDesc = binding.tvDescriptionIntro
        private var ivSlideIcon = binding.ivSlideIconIntro

        fun bind(introSlide: IntroSlide) {
            textTitle.text = introSlide.title
            textDesc.text = introSlide.description
            ivSlideIcon.setImageResource(introSlide.icon)
        }
    }

    override fun getItemCount(): Int {
        return introSlides.size
    }

    override fun onBindViewHolder(holder: IntoSlideViewHolder, position: Int) {
        holder.bind(introSlides[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntoSlideViewHolder {
        return IntoSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slide_item_container_user,
                parent,
                false
            )
        )
    }
}