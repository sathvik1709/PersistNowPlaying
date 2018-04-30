package com.sathvik1709.nowplayingpersistclient.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.reactivex.Flowable

@Dao
interface SongsDao {

    @Query("SELECT * FROM songs ORDER BY time DESC")
    fun getAllForMainList() : Flowable<List<SongEntity>>

    @Insert(onConflict = REPLACE)
    fun insertSong(song : SongEntity)

    @Query("SELECT * FROM songs ORDER BY time DESC LIMIT 1")
    fun getLastInsertedSong() : SongEntity

    @Update()
    fun updateLastSongTime(song: SongEntity)

    @Update()
    fun setFav(song: SongEntity)

}