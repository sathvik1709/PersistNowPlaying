package com.sathvik1709.nowplayingpersistclient.activities.archive_acticity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.sathvik1709.nowplayingpersistclient.R
import com.sathvik1709.nowplayingpersistclient.database.SongEntity
import com.sathvik1709.nowplayingpersistclient.util.DateTimeUtil


class ArchiveListAdapter(private val songsList : List<SongEntity>, val dateTimeUtil: DateTimeUtil) : RecyclerView.Adapter<ArchiveListAdapter.ViewHolder>() {

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var songNameTv = view.findViewById<TextView>(R.id.song_name)
        var albumNameTv = view.findViewById<TextView>(R.id.album_name)
        var timeTv = view.findViewById<TextView>(R.id.time)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchiveListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_archive_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.songNameTv.text = songsList[position].songName
        holder.albumNameTv.text = songsList[position].albumName
        holder.timeTv.text = dateTimeUtil.convertToMainListFormat(songsList[position].time)
    }

    override fun getItemCount() = songsList.size
}