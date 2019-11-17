package br.com.joaopaulosj.vanhackathon2019.data.repositories

import br.com.joaopaulosj.vanhackathon2019.data.remote.models.JobResponse
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.JobsResponse
import br.com.joaopaulosj.vanhackathon2019.utils.Resources
import com.google.gson.Gson
import io.reactivex.Single

object JobsRepository {
	
	fun getJobs(): Single<List<JobResponse>> {
		val mock = Resources.read("mock_jobs")
		val response = Gson().fromJson<JobsResponse>(mock, JobsResponse::class.java)
		return Single.just(response.jobs)
	}
	
}