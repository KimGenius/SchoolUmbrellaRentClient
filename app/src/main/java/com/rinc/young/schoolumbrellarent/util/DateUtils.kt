package com.rinc.young.schoolumbrellarent.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by young on 2017-07-19/오전 10:36
 * This Project is SchoolUmbrellaRent
 */
object DateUtils {
    fun calDate(date: String): Long {
        val gc = GregorianCalendar()
        val formatter = SimpleDateFormat("yyyy-MM-dd")
        val d = gc.time
        val str = formatter.format(d)
        val beginDate = formatter.parse(str)
        val endDate = formatter.parse(date)
        val diff = endDate.time - beginDate.time
        val diffDays = diff / (24 * 60 * 60 * 1000)
        return diffDays
    }
}