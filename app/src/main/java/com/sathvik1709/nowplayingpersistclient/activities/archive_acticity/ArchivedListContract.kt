package com.sathvik1709.nowplayingpersistclient.activities.archive_acticity

import com.sathvik1709.nowplayingpersistclient.database.SongEntity

interface ArchivedListContract {

    interface View {
        fun showNoListView();
        fun showFullListView(list: List<SongEntity>);
    }

    interface Presenter {
        fun loadList()
        fun updateSongFav(songEntity: SongEntity)
    }

}