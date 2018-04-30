package com.sathvik1709.nowplayingpersistclient.activities.archive_acticity

import android.widget.AbsListView
import com.sathvik1709.nowplayingpersistclient.activities.archive_acticity.ArchivedListActivity
import com.sathvik1709.nowplayingpersistclient.activities.archive_acticity.ArchivedListContract
import com.sathvik1709.nowplayingpersistclient.activities.archive_acticity.ArchivedListPresenter
import com.sathvik1709.nowplayingpersistclient.di.ActicityScope
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class ArchivedListModule {

    @ActicityScope
    @Binds
    abstract fun provideArchivedListPresenter(archivedListPresenter : ArchivedListPresenter) : ArchivedListContract.Presenter

    @ActicityScope
    @Binds
    abstract fun provideArchivedListView(archivedListView : ArchivedListActivity) : ArchivedListContract.View

}