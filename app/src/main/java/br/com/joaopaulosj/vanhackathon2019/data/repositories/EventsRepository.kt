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
	
	fun getEvents(): Single<List<EventResponse>> {
		val mock = Resources.read("mock_events")
		val response = Gson().fromJson<EventsResponse>(mock, EventsResponse::class.java)
		events = response.result
		return Single.just(events)
	}
	
	fun getCurrentFilters(): List<String> {
		return listOf("Canada", "United Kingdom", "0-3 years", "4-6 years")
	}
	
}