package com.sathvik1709.nowplayingpersistclient.activities.fav_activity

import com.sathvik1709.nowplayingpersistclient.database.SongEntity


interface FavsListContract {

    interface View {
        fun showNoListView();
        fun showFavsListView(list: List<SongEntity>);
    }

    interface Presenter {
        fun loadList()
    }

}