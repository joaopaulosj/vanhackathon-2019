package br.com.joaopaulosj.vanhackathon2019.data.remote.models

import br.com.joaopaulosj.vanhackathon2019.AppConstants
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.dateFromString
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.displayName
import java.util.*

class NotificationsResponse(
    val result: List<NotificationResponse> = emptyList()
)

class NotificationResponse(
    val id: Int,
    val title: String,
    val isRead: Boolean,
    val description: String,
    val date: String
) {

    fun getDate(): Date? {
        return date.dateFromString(AppConstants.DATE_FORMAT)
    }

}