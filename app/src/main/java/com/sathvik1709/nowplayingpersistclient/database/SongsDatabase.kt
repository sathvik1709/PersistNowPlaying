package com.sathvik1709.nowplayingpersistclient.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [SongEntity::class], version = 5)
abstract class SongsDatabase : RoomDatabase() {

    abstract fun songsDao() : SongsDao

}