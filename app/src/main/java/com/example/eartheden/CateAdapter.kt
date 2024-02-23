package com.example.eartheden

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class CateAdapter (val cateList: List<CateModel> ):RecyclerView.Adapter<ViewHolder>(){

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.model, parent ,false))
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val dataModel = cateList[position]
            holder.textTitleItem.text = dataModel.title
            Picasso.get().load(dataModel.Image)
                .error(R.drawable.imgloading)
                .placeholder(R.drawable.imgloading)
                .into(holder.imageView)




        }

        override fun getItemCount(): Int {
            return cateList.size
        }


    }

