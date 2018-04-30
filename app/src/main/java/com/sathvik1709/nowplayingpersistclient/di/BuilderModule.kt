package com.sathvik1709.nowplayingpersistclient.di

import com.sathvik1709.nowplayingpersistclient.activities.archive_acticity.ArchivedListActivity
import com.sathvik1709.nowplayingpersistclient.service.NowPlayingReceiverService
import dagger.Module
import dagger.android.ContributesAndroidInjector
import com.sathvik1709.nowplayingpersistclient.activities.archive_acticity.ArchivedListModule



@Module
abstract class BuilderModule {

    @ActicityScope
    @ContributesAndroidInjector(modules = [ArchivedListModule::class])
    abstract fun bindMainActivity(): ArchivedListActivity

    @ContributesAndroidInjector
    abstract fun bindService() : NowPlayingReceiverService
}