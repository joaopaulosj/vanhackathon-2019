package br.com.joaopaulosj.vanhackathon2019.data.repositories

import br.com.joaopaulosj.vanhackathon2019.data.remote.models.JobResponse
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.JobsResponse
import br.com.joaopaulosj.vanhackathon2019.utils.Resources
import com.google.gson.Gson
import io.reactivex.Single

object JobsRepository {

    private var jobs = listOf<JobResponse>()
    private var filtered = listOf<JobResponse>()

    fun getJobs(): Single<List<JobResponse>> {
        val mock = Resources.read("mock_jobs")
        val response = Gson().fromJson<JobsResponse>(mock, JobsResponse::class.java)
        jobs = response.result
        filtered = jobs
        return Single.just(jobs)
    }

    fun filter(query: String): List<JobResponse> {
        filtered = jobs.filter {
            it.positionName.contains(query, true)
                    || it.city.contains(query, true)
                    || it.country.contains(query, true)
                    || it.description.contains(query, true)
        }
        return filtered
    }

    fun setFavorite(jobId: Int): Single<List<JobResponse>> {
        filtered.find { it.id == jobId }?.let {
            it.favorited = !it.favorited
        }

        return Single.just(filtered)
    }

    fun apply(jobId: Int): Single<List<JobResponse>> {
        filtered.find { it.id == jobId }?.let {
            it.applied = !it.applied
        }

        return Single.just(filtered)
    }

    fun getCurrentFilters(): List<String> {
        return listOf("Canada", "United Kingdom", "0-3 years", "4-6 years")
    }

}