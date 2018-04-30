package com.sathvik1709.nowplayingpersistclient.service

import android.content.Context
import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import com.sathvik1709.nowplayingpersistclient.database.SongEntity
import com.sathvik1709.nowplayingpersistclient.repo.RepoClient
import com.sathvik1709.nowplayingpersistclient.util.SongNameUtil
import dagger.android.AndroidInjection
import org.joda.time.DateTime
import javax.inject.Inject



class NowPlayingReceiverService : NotificationListenerService() {

    @Inject lateinit var context : Context

    @Inject lateinit var localRepoClient: RepoClient

    override fun onCreate() {
        AndroidInjection.inject(this);
        super.onCreate()
    }

    override fun onNotificationPosted(notification: StatusBarNotification) {
       super.onNotificationPosted(notification)

        if(notification.packageName == "com.google.intelligence.sense"){
            val songNameWithAlbum = notification.notification.extras.getString("android.title")
            val(songName, albumName) = SongNameUtil().splitSongAndAlbum(songNameWithAlbum)

            if(songName != null || albumName != null){
                val songEntity = SongEntity()
                songEntity.songName = songName
                songEntity.albumName = albumName
                songEntity.isFav = false
                songEntity.time = getCurrentTimeInMillis()

                val lastSongEntity = localRepoClient.getLastInertedSong()

                if(lastSongEntity == null){
                    localRepoClient.addNewSongEntity(songEntity)
                } else {
                    if((songName != lastSongEntity.songName)){
                        localRepoClient.addNewSongEntity(songEntity)
                    }else{

                        lastSongEntity.time = getCurrentTimeInMillis()
                        localRepoClient.updateLastSongTime(lastSongEntity)
                    }
                }
            }
        }

    }

    private fun getCurrentTimeInMillis(): Long {
        return DateTime().millis;
    }


}