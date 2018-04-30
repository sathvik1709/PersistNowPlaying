package com.sathvik1709.nowplayingpersistclient.di

import android.app.Application
import com.sathvik1709.nowplayingpersistclient.NowPlayingApp
import com.sathvik1709.nowplayingpersistclient.activities.archive_acticity.ArchivedListActivity
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    AndroidSupportInjectionModule::class,
    BuilderModule::class
])
interface AppComponent {

    @Component.Builder
    interface Builder {
        fun build() : AppComponent
        @BindsInstance fun application(nowPlayingApp: NowPlayingApp) : Builder
    }
    fun inject(app : NowPlayingApp)
}