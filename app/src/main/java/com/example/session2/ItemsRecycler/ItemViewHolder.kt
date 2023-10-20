package com.example.session2.ItemsRecycler
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.session2.Models.BasicArticle
import com.example.session2.R

class ItemViewHolder(ItemView:View):ViewHolder(ItemView) {
    var title:TextView = ItemView.findViewById(R.id.Title)
    var lastDate:TextView = ItemView.findViewById(R.id.Date)
    fun render(article:BasicArticle){
        title.text = article.title
        lastDate.text = "Ultima fecha de precio: ${article.lastDate}"
    }
}