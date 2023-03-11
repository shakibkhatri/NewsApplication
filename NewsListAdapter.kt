package com.example.newsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter( private val listner: NewsItemCliked): RecyclerView.Adapter<NewsListAdapter.NewsViewHolder>() {

    val items: ArrayList<News> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        val viewHolder = NewsViewHolder(view)
        view.setOnClickListener{
            listner.onItemClicked(items[viewHolder.bindingAdapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleview.text = currentItem.title
        holder.author.text = currentItem.author
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
    }

    fun updateNews(updateNews: ArrayList<News>){
        items.clear()
        items.addAll(updateNews)

        notifyDataSetChanged()
    }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleview: TextView = itemView.findViewById(R.id.titlenews)
        val image: ImageView = itemView.findViewById(R.id.newsimage)
        val author: TextView = itemView.findViewById(R.id.newsAuthor)
    }

    interface NewsItemCliked {
        fun onItemClicked(item: News)
    }
}