package br.com.joaopaulosj.vanhackathon2019.data.repositories

import br.com.joaopaulosj.vanhackathon2019.data.remote.models.NotificationResponse
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.NotificationsResponse
import br.com.joaopaulosj.vanhackathon2019.utils.Resources
import com.google.gson.Gson
import io.reactivex.Single

object NotificationsRepository {

    fun getNotifications(): Single<List<NotificationResponse>> {
        val mock = Resources.read("mock_notification")
        val response = Gson().fromJson<NotificationsResponse>(mock, NotificationsResponse::class.java)
        return Single.just(response.result)
    }

}