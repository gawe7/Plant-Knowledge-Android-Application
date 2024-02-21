package com.example.eartheden

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eartheden.ui.menu.PlantModel
import com.squareup.picasso.Picasso

class Adapter  (val PlantList:List<PlantModel>): RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.model, parent ,false))
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = PlantList[position]
        holder.textTitleItem.text = dataModel.title
        Picasso.get().load(dataModel.img)
            .error(R.drawable.imgloading)
            .placeholder(R.drawable.imgloading)
            .into(holder.imageView)



    }

    override fun getItemCount(): Int {
        return PlantList.size
    }


}