package com.project1.fatigueapplication.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.project1.fatigueapplication.R
import com.project1.fatigueapplication.data.viewdata.DataParcel

class DataAdapter(
    private val context: Context,
    private val images: List<DataParcel>,
    private val listener: (DataParcel) -> Unit
) : RecyclerView.Adapter<DataAdapter.ImageViewHolder>() {
    class ImageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val imageSrc = view.findViewById<ImageView>(R.id._image)
        private val textName = view.findViewById<TextView>(R.id._name)
        private val textNumber = view.findViewById<TextView>(R.id._number)
        private val textEmail = view.findViewById<TextView>(R.id._email)
        fun bindView(image: DataParcel, listener: (DataParcel) -> Unit) {
            imageSrc.setImageResource(image.iImage)
            textName.text = image.iName
            textNumber.text = image.iBangkitNumber
            textEmail.text = image.iBangkitEmail
            itemView.setOnClickListener { listener(image) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder =
            ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.item_image, parent, false))

    override fun getItemCount(): Int = images.size

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bindView(images[position], listener)
    }
}