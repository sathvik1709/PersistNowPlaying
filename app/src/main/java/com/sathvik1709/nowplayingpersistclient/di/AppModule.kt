package com.sathvik1709.nowplayingpersistclient.di

import android.arch.persistence.room.Room
import android.content.Context
import com.sathvik1709.nowplayingpersistclient.NowPlayingApp
import com.sathvik1709.nowplayingpersistclient.database.SongsDao
import com.sathvik1709.nowplayingpersistclient.database.SongsDatabase
import com.sathvik1709.nowplayingpersistclient.repo.LocalRepoClient
import com.sathvik1709.nowplayingpersistclient.repo.RepoClient
import com.sathvik1709.nowplayingpersistclient.util.DateTimeUtil
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(nowPlayingApp: NowPlayingApp) : Context = nowPlayingApp.applicationContext

    @Provides
    @Singleton
    fun provideDatabase(context : Context) : SongsDatabase = Room.databaseBuilder(context, SongsDatabase::class.java, "songs-db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun providesToDoDao(songsDatabase: SongsDatabase) : SongsDao = songsDatabase.songsDao()

    @Provides
    @Singleton
    fun providesLocalRepoClient(songsDao: SongsDao) : RepoClient = LocalRepoClient(songsDao)

    @Provides
    @Singleton
    fun provideTimeUtil() : DateTimeUtil = DateTimeUtil()

}