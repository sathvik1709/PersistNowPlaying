package com.sathvik1709.nowplayingpersistclient.repo

import com.sathvik1709.nowplayingpersistclient.database.SongEntity
import com.sathvik1709.nowplayingpersistclient.database.SongsDao
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class LocalRepoClient @Inject constructor(songsDao: SongsDao) : RepoClient {

    var songsDao = songsDao;

    override fun getSongsList(): Flowable<List<SongEntity>> {
        return songsDao.getAllForMainList()
    }

    override fun addNewSongEntity(songEntity: SongEntity) {
        songsDao.insertSong(songEntity)
    }

    override fun getLastInertedSong(): Single<SongEntity> {
        return songsDao.getLastInsertedSong()
    }

    override fun updateLastSongTime(songEntity: SongEntity) {
        songsDao.updateLastSongTime(songEntity)
    }

    override fun setFavSong(songEntity: SongEntity) {
        songsDao.setFav(songEntity)
    }

    override fun getFavSongsList(): Single<List<SongEntity>> {
        return songsDao.getFavSongsList()
    }

}