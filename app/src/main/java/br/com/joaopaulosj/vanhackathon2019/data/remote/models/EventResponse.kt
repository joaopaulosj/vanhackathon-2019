package br.com.joaopaulosj.vanhackathon2019.data.remote.models

import br.com.joaopaulosj.vanhackathon2019.AppConstants
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.dateFromString
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.displayName
import java.util.*

class EventsResponse(
    val result: List<EventResponse> = emptyList()
)

class EventResponse(
    val id: Int,
    val name: String,
    val country: String,
    val city: String,
    val state: String,
    val slug: String,
    val promoted: Boolean,
    val startDate: String,
    val endDate: String,
    val startApplicationDate: String,
    val endApplicationDate: String,
    val isOnline: Boolean,
    val eventType: Int,
    val applied: Boolean,
    val possibleToApply: Boolean,
    val subtitle: String,
    val theEvent: String,
    val theCandidates: String,
    val flag: String,
    val cover: String,
    val thumb: String
) {

    fun getFlagResName(): String {
        return "flag_" + country.replace(" ", "").toLowerCase()
    }

    fun getStart(): Date? {
        return startDate.dateFromString(AppConstants.DATE_FORMAT)
    }

    fun getEnd(): Date? {
        return endDate.dateFromString(AppConstants.DATE_FORMAT)
    }

    fun getDeadline(): String {
        val end = endApplicationDate.dateFromString(AppConstants.DATE_FORMAT)
        return "<b>Deadline</b>: ${end?.displayName("MM/dd/yyyy")}"
    }

    fun getDate(): String {
        val start = startDate.dateFromString(AppConstants.DATE_FORMAT)
        val end = endDate.dateFromString(AppConstants.DATE_FORMAT)
        return "${start?.displayName("MMMM d")} - ${end?.displayName("d, yyyy")}".capitalize()
    }

}