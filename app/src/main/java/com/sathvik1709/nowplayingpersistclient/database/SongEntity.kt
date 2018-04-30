package com.sathvik1709.nowplayingpersistclient.database

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "songs")
class SongEntity() {
    @PrimaryKey(autoGenerate = true) var id: Int= 0
    @ColumnInfo(name = "song_name") lateinit var songName : String
    @ColumnInfo(name = "album_name") lateinit var albumName : String
    @ColumnInfo(name = "is_fav") var isFav : Boolean = false
    @ColumnInfo(name = "time") var time : Long = 0
}
