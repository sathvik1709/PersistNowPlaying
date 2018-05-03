package com.sathvik1709.nowplayingpersistclient.activities.archive_acticity

import com.sathvik1709.nowplayingpersistclient.di.ActicityScope
import dagger.Binds
import dagger.Module

@Module
abstract class ArchivedListModule {
    @ActicityScope
    @Binds
    abstract fun provideArchivedListPresenter(archivedListPresenter : ArchivedListPresenter) : ArchivedListContract.Presenter

    @ActicityScope
    @Binds
    abstract fun provideArchivedListView(archivedListView : ArchivedListActivity) : ArchivedListContract.View


}