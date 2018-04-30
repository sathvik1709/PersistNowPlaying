package com.sathvik1709.nowplayingpersistclient.repo

import com.sathvik1709.nowplayingpersistclient.database.SongEntity
import io.reactivex.Flowable

interface RepoClient {

    fun getSongsList() : Flowable<List<SongEntity>>
    fun addNewSongEntity(songEntity: SongEntity)
    fun getLastInertedSong() : SongEntity
    fun updateLastSongTime(songEntity: SongEntity)
    fun setFavSong(songEntity: SongEntity)

}