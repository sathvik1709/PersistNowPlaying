package com.sathvik1709.nowplayingpersistclient.activities.archive_acticity

import agency.tango.android.avatarview.AvatarPlaceholder
import agency.tango.android.avatarview.views.AvatarView
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.sathvik1709.nowplayingpersistclient.R
import com.sathvik1709.nowplayingpersistclient.database.SongEntity
import com.sathvik1709.nowplayingpersistclient.util.DateTimeUtil
import io.reactivex.subjects.PublishSubject
import org.jetbrains.annotations.Nullable


class ArchiveListAdapter(val songsList : List<SongEntity>, @Nullable var dateTimeUtil: DateTimeUtil, val showFavIcon : Boolean = true) : RecyclerView.Adapter<ArchiveListAdapter.ViewHolder>() {

    val onFavClicked = PublishSubject.create<SongEntity>()
    val onViewClicked = PublishSubject.create<SongEntity>()


    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        var itemContainer = view.findViewById<ConstraintLayout>(R.id.item_container)
        var songNameTv = view.findViewById<TextView>(R.id.song_name)
        var albumNameTv = view.findViewById<TextView>(R.id.album_name)
        var timeTv = view.findViewById<TextView>(R.id.time)
        var favIcon = view.findViewById<ImageView>(R.id.favIcon)
        var avatarIcon = view.findViewById<AvatarView>(R.id.avatarView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArchiveListAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_archive_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.songNameTv.text = songsList[position].songName
        holder.albumNameTv.text = songsList[position].albumName

        if (dateTimeUtil == null) {
            holder.timeTv.visibility = View.GONE
        } else {
            holder.timeTv.text = dateTimeUtil.convertToMainListFormat(songsList[position].time)
        }

        if(showFavIcon){
            if(songsList[position].isFav){
                holder.favIcon.setImageResource( R.drawable.ic_fav )
            }else{
                holder.favIcon.setImageResource( R.drawable.ic_unfav )
            }

            holder.favIcon.setOnClickListener {
                onFavClicked.onNext(songsList[position])
            }
        } else {
          holder.favIcon.visibility = View.GONE
        }

        var avatarPlaceholder : AvatarPlaceholder = AvatarPlaceholder(songsList[position].songName)
        holder.avatarIcon.setImageDrawable(avatarPlaceholder)

        holder.itemContainer.setOnClickListener {
            onViewClicked.onNext(songsList[position])
        }



    }

    override fun getItemCount() = songsList.size
}