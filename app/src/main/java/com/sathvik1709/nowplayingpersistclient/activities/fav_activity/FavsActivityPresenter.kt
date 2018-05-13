package com.sathvik1709.nowplayingpersistclient.activities.fav_activity

import com.sathvik1709.nowplayingpersistclient.repo.RepoClient
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class FavsActivityPresenter @Inject constructor(view: FavsListContract.View) : FavsListContract.Presenter {

    @Inject
    lateinit var repoClient : RepoClient

    val view : FavsListContract.View = view

    override fun loadList() {
        repoClient.getFavSongsList()
                .subscribeOn(Schedulers.io())
                .subscribe({ favsSongs ->
                    view.showFavsListView(favsSongs)
                },{ e ->
                    view.showNoListView()
                })
    }
}