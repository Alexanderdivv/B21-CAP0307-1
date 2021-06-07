package com.project1.fatigueapplication.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.project1.fatigueapplication.R
import com.project1.fatigueapplication.data.viewdata.IntroSlider
import com.project1.fatigueapplication.databinding.ItemSlideContainerBinding

class IntroSliderAdapter(private val introSliders: List<IntroSlider>) :
    RecyclerView.Adapter<IntroSliderAdapter.IntoSlideViewHolder>() {

    inner class IntoSlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemSlideContainerBinding.bind(view)

        private var textTitle = binding.tvTitleSlideIntro
        private var textDesc = binding.tvDescriptionIntro
        private var ivSlideIcon = binding.ivSlideIconIntro

        fun bind(introSlider: IntroSlider) {
            textTitle.text = introSlider.title
            textDesc.text = introSlider.description
            ivSlideIcon.setImageResource(introSlider.icon)
        }
    }

    override fun getItemCount(): Int {
        return introSliders.size
    }

    override fun onBindViewHolder(holder: IntoSlideViewHolder, position: Int) {
        holder.bind(introSliders[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntoSlideViewHolder {
        return IntoSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_slide_container,
                parent,
                false
            )
        )
    }
}