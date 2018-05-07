package com.sathvik1709.nowplayingpersistclient.di

import com.sathvik1709.nowplayingpersistclient.activities.archive_acticity.ArchivedListActivity
import com.sathvik1709.nowplayingpersistclient.activities.archive_acticity.ArchivedListModule
import com.sathvik1709.nowplayingpersistclient.activities.fav_activity.FavsActivity
import com.sathvik1709.nowplayingpersistclient.activities.fav_activity.FavsActivityModule
import com.sathvik1709.nowplayingpersistclient.service.NowPlayingReceiverService
import dagger.Module
import dagger.android.ContributesAndroidInjector



@Module
abstract class BuilderModule {

    @ContributesAndroidInjector
    abstract fun bindService() : NowPlayingReceiverService

    @ActicityScope
    @ContributesAndroidInjector(modules = [ArchivedListModule::class])
    abstract fun bindArchivedListActivity(): ArchivedListActivity

    @ActicityScope
    @ContributesAndroidInjector(modules = [FavsActivityModule::class])
    abstract fun bindFavsActivityActivity(): FavsActivity
}