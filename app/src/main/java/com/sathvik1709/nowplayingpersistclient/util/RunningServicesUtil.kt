package com.sathvik1709.nowplayingpersistclient.util

import android.app.ActivityManager
import android.content.Context
import com.sathvik1709.nowplayingpersistclient.service.NowPlayingReceiverService

class RunningServicesUtil {

    fun isNLServiceRunning(context: Context): Boolean {
        val manager = context.applicationContext.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Integer.MAX_VALUE)) {
            if (NowPlayingReceiverService::class.java!!.name == service.service.className) {
                return true
            }
        }
        return false
    }

}