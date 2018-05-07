package com.sathvik1709.nowplayingpersistclient.activities.fav_activity

import android.util.Log
import com.sathvik1709.nowplayingpersistclient.repo.RepoClient
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavsActivityPresenter @Inject constructor(favsActivity: FavsListContract.View) : FavsListContract.Presenter {

    @Inject
    lateinit var repoClient : RepoClient

    override fun loadList() {
        repoClient.getFavSongsList()
                .subscribeOn(Schedulers.io())
                .subscribe({ favSongs -> Log.d("fav", favSongs.toString()) })
    }
}