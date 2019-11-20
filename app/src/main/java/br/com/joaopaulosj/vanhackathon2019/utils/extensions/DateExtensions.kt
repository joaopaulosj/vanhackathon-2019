package br.com.joaopaulosj.vanhackathon2019.utils.extensions

import br.com.joaopaulosj.vanhackathon2019.AppConstants
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun String.dateFromString(dateFormat: String, locale: Locale = Locale.CANADA): Date? {
	return try {
		val oldDf = SimpleDateFormat(dateFormat, locale)
		val date = oldDf.parse(this)
		date
	} catch (e: Exception) {
		e.printStackTrace()
		null
	}
}

fun Date.displayName(format: String): String {
	val cal = Calendar.getInstance()
	cal.time = this
	val sdf = SimpleDateFormat(format, Locale.getDefault())
	return sdf.format(cal.time)
}

fun String.calendarFromString(dateFormat: String, locale: Locale = Locale(AppConstants.LANGUAGE_PT, AppConstants.COUNTRY_BR),
                              isUTC: Boolean = false): Calendar? {
	return try {
		val cal = Calendar.getInstance()
		if (isUTC)
			cal.timeZone = TimeZone.getTimeZone("gmt")
		val date = this.dateFromString(dateFormat, locale)
		cal.time = date
		return cal
	} catch (e: Exception) {
		e.printStackTrace()
		null
	}
}



