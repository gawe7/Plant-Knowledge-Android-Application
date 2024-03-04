package com.example.eartheden

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class DetailModelAdapter(private val data: List<DetailModel>) :
    RecyclerView.Adapter<DetailModelAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val plantImage: ImageView = itemView.findViewById(R.id.detailImage)
        val plantDescription: TextView = itemView.findViewById(R.id.detailDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context

        val item = data[position]

        Picasso.get().load(item.dataImage)
            .error(R.drawable.imgloading)
            .placeholder(R.drawable.imgloading)
            .into(holder.plantImage)
        holder.plantDescription.text = item.dataDesc

        holder.itemView.setOnClickListener {
            val intent = Intent(context, Detail::class.java)
            intent.putExtra("Image", item.dataImage)
            intent.putExtra("Description", item.dataDesc)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}
