package com.sathvik1709.nowplayingpersistclient.util

import android.content.ComponentName
import android.content.Intent
import android.provider.Settings

fun Intent.getNotificationsAccessSettings(): Intent? {
    return Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
}

fun Intent.getNowPlayingSettingsIntent(settingsPackage: String): Intent? {
    val nowPlayingSettingsIntent = Intent(Intent.ACTION_MAIN)
    nowPlayingSettingsIntent.component = ComponentName.unflattenFromString(settingsPackage)
    nowPlayingSettingsIntent.addCategory(Intent.CATEGORY_LAUNCHER)
    nowPlayingSettingsIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    return nowPlayingSettingsIntent
}
