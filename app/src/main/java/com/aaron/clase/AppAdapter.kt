package com.aaron.clase

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class AppAdapter (context: Context, feedEntries: ArrayList<FeedEntry>): RecyclerView.Adapter<AppAdapter.ViewHolder>{
    private var localContext: Context? = null
    private var localFeedEntries: ArrayList<FeedEntry>? = null

    init {
        localContext = context
        localFeedEntries = feedEntries
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppAdapter.ViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(localContext)
        val view: View = layoutInflater.inflate(R.layout.row_item, parent, false)
        return ViewHolder(view)

    }

    override fun onBindViewHolder(holder: AppAdapter.ViewHolder, position: Int) {
        val currentFeedEntry: FeedEntry = localFeedEntries!![position]
        holder.textArtist.text = currentFeedEntry.artist
        holder.textDesc.text = currentFeedEntry.summary
        holder.textName.text = currentFeedEntry.name

    }

    override fun getItemCount(): Int {
        return localFeedEntries?.size ?:0
    }

    class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        val textName: TextView = v.findViewById(R.id.textViewName)
        val textArtist: TextView = v.findViewById(R.id.textViewArtist)
        val textDesc: TextView = v.findViewById(R.id.textViewDesc)
    }
}