package com.sathvik1709.nowplayingpersistclient.util

class SongNameUtil {

    fun splitSongAndAlbum(songNameWithAlbum : String) : Pair<String, String> {
        val split = songNameWithAlbum.split("by")
        return Pair(split[0], split[1])
    }

}