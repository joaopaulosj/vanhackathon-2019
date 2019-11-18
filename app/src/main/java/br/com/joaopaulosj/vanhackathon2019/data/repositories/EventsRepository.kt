package br.com.joaopaulosj.vanhackathon2019.data.repositories

import br.com.joaopaulosj.vanhackathon2019.data.remote.models.EventResponse
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.EventsResponse
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.JobResponse
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.JobsResponse
import br.com.joaopaulosj.vanhackathon2019.utils.Resources
import com.google.gson.Gson
import io.reactivex.Single

object EventsRepository {

    private var events = listOf<EventResponse>()
    private var filtered = listOf<EventResponse>()

    fun getEvents(): Single<List<EventResponse>> {
        val mock = Resources.read("mock_events")
        val response = Gson().fromJson<EventsResponse>(mock, EventsResponse::class.java)
        events = response.result
        filtered = events
        return Single.just(events)
    }

    fun filter(query: String): List<EventResponse> {
        filtered = events.filter {
            it.name.contains(query, true)
                    || it.city.contains(query, true)
                    || it.country.contains(query, true)
                    || it.theEvent.contains(query, true)
        }
        return filtered
    }

    fun getCurrentFilters(): List<String> {
        return listOf("Canada", "United Kingdom", "0-3 years", "4-6 years")
    }

}