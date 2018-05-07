package com.sathvik1709.nowplayingpersistclient.activities.fav_activity

import com.sathvik1709.nowplayingpersistclient.di.ActicityScope
import dagger.Binds
import dagger.Module

@Module
abstract class FavsActivityModule {

    @ActicityScope
    @Binds
    abstract fun provideFavsListPresenter(favsActivityPresenter: FavsActivityPresenter) : FavsListContract.Presenter

    @ActicityScope
    @Binds
    abstract fun provideFavsView(favsActivity: FavsActivity) : FavsListContract.View

}