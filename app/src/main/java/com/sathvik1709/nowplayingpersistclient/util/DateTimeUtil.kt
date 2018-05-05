package com.sathvik1709.nowplayingpersistclient.util

import org.joda.time.DateTime
import org.ocpsoft.prettytime.PrettyTime


class DateTimeUtil {

    fun convertToMainListFormat(time : Long) : String{
        var prettyTime = PrettyTime()
        val dt = DateTime(time)
        return prettyTime.format(dt.toDate())
    }

}