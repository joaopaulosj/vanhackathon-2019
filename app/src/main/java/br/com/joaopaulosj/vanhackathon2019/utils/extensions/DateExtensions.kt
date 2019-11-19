package br.com.joaopaulosj.vanhackathon2019.utils.extensions

import br.com.joaopaulosj.vanhackathon2019.AppConstants
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

private const val SECOND_MILLIS = 1000f
private const val MINUTE_MILLIS = 60 * SECOND_MILLIS
private const val HOUR_MILLIS = 60 * MINUTE_MILLIS
private const val DAY_MILLIS = 24 * HOUR_MILLIS
private const val MONTH_MILLIS = 30 * DAY_MILLIS

fun Date.inMillis(): Long {
	val calendar = Calendar.getInstance()
	calendar.time = this
	return calendar.timeInMillis
}

fun String.inMillis(dateFormat: String) =
		this.dateFromString(dateFormat)?.inMillis()

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


fun Date.isToday(): Boolean {
	val today = Calendar.getInstance()
	val selected = Calendar.getInstance()
	selected.time = this
	
	return today.get(Calendar.MONTH) == selected.get(Calendar.MONTH)
			&& today.get(Calendar.YEAR) == selected.get(Calendar.YEAR)
			&& today.get(Calendar.DAY_OF_MONTH) == selected.get(Calendar.DAY_OF_MONTH)
}

fun Date.isPast(): Boolean {
	val today = Calendar.getInstance()
	val selected = Calendar.getInstance()
	selected.time = this
	
	return today.after(selected)
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



