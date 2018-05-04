package com.sathvik1709.nowplayingpersistclient.util

import org.joda.time.DateTime
import org.joda.time.LocalDate
import org.joda.time.format.DateTimeFormat


class DateTimeUtil {

    val main_list_format = "MMM dd yyyy"

    fun convertToMainListFormat(time : Long) : String{

        val dtf = DateTimeFormat.forPattern(main_list_format)
        val dt = DateTime(time)

        if(dt.toLocalDate().equals(LocalDate.now())){
            return "to do" // TODO
        }else if(dt.toLocalDate().equals(LocalDate.now().minusDays(1))){
            return "Yesterday"
        }

        val datetime = dtf.print(dt)
        return datetime
    }
}