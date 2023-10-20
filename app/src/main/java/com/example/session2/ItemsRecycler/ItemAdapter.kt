package com.example.session2.ItemsRecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.session2.Models.BasicArticle
import com.example.session2.R

class ItemAdapter(var list:List<BasicArticle>): Adapter<ItemViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        var inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater.inflate( R.layout.article_list,parent, false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.render(list.get(position))
    }
}