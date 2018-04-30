package com.sathvik1709.nowplayingpersistclient

import android.app.Activity
import android.app.Application
import android.app.Service
import android.content.IntentFilter
import com.sathvik1709.nowplayingpersistclient.di.AppComponent
import com.sathvik1709.nowplayingpersistclient.di.DaggerAppComponent
import com.sathvik1709.nowplayingpersistclient.receiver.NowPlayingReceiver
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.HasServiceInjector
import javax.inject.Inject

class NowPlayingApp : Application(), HasActivityInjector, HasServiceInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var serviceInjector: DispatchingAndroidInjector<Service>

    lateinit var appComponent: AppComponent

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    override fun serviceInjector(): AndroidInjector<Service> = serviceInjector

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().application(this).build()
        appComponent.inject(this);

        registerReceiver(NowPlayingReceiver(), IntentFilter(getString(R.string.song_broadcast_action)))
    }
}