package br.com.joaopaulosj.vanhackathon2019.data.repositories

import br.com.joaopaulosj.vanhackathon2019.data.remote.models.JobResponse
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.JobsResponse
import br.com.joaopaulosj.vanhackathon2019.utils.Resources
import com.google.gson.Gson
import io.reactivex.Single

object JobsRepository {
	
	private var jobs = listOf<JobResponse>()
	
	fun getJobs(): Single<List<JobResponse>> {
		val mock = Resources.read("mock_jobs")
		val response = Gson().fromJson<JobsResponse>(mock, JobsResponse::class.java)
		jobs = response.jobs
		return Single.just(jobs)
	}
	
	fun setFavorite(jobId: Int): Single<List<JobResponse>> {
		jobs.find { it.id == jobId }?.let {
			it.favorited = !it.favorited
		}
		
		return Single.just(jobs)
	}
	
	fun apply(jobId: Int): Single<List<JobResponse>> {
		jobs.find { it.id == jobId }?.let {
			it.applied = !it.applied
		}
		
		return Single.just(jobs)
	}
	
}