package com.example.eartheden.ui.notifications

import com.example.eartheden.ViewHolder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eartheden.NotiModel
import com.example.eartheden.R
import com.squareup.picasso.Picasso

class NotiAdapter(private val plantList: ArrayList<NotiModel>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.detailmodel, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = plantList[position]
        holder.textTitleItem.text = dataModel.title
        Picasso.get().load(dataModel.img)
            .error(R.drawable.imgloading)
            .placeholder(R.drawable.imgloading)
            .into(holder.imageView)
    }


    override fun getItemCount(): Int {
        return plantList.size
    }
}
