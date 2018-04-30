package com.sathvik1709.nowplayingpersistclient.activities.archive_acticity

import android.util.Log
import com.sathvik1709.nowplayingpersistclient.database.SongEntity
import com.sathvik1709.nowplayingpersistclient.repo.RepoClient
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Inject

class ArchivedListPresenter @Inject constructor(archivedView: ArchivedListContract.View) : ArchivedListContract.Presenter {

    @Inject
    lateinit var repoClient: RepoClient

    var archivedView: ArchivedListContract.View = archivedView

    override fun loadList() {
        repoClient.getSongsList()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                { list ->
                    run {
                        if (list.isEmpty()) {
                            archivedView.showNoListView()
                        } else {
                            archivedView.showFullListView(list)
                        }
                    }
                },
                {
                    t -> Log.d("Error", t.message)
                }
        )
    }

    override fun updateSongFav(songEntity: SongEntity) {
        repoClient.setFavSong(songEntity)
    }
}