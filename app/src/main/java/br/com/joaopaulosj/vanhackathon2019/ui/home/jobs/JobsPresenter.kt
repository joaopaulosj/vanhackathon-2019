package br.com.joaopaulosj.vanhackathon2019.ui.home.jobs

import br.com.joaopaulosj.vanhackathon2019.data.remote.models.JobResponse
import br.com.joaopaulosj.vanhackathon2019.data.repositories.JobsRepository
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.singleSubscribe
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable

class JobsPresenter : JobsContract.Presenter {

    private var view: JobsContract.View? = null
    private var disposable = CompositeDisposable()
    private var jobs = listOf<JobResponse>()
    private var filtered = listOf<JobResponse>()

    override fun attachView(mvpView: JobsContract.View?) {
        view = mvpView
    }

    override fun detachView() {
        view = null
        disposable.dispose()
    }

    override fun loadItems() {
        view?.displayLoading(true)
        disposable.add(JobsRepository.getJobs().singleSubscribe(
            onSuccess = {
                jobs = it
                filtered = it
                view?.displayLoading(false)
                view?.displayItems(it)
                view?.displayFilters(JobsRepository.getCurrentFilters())
            },
            onError = {
                view?.displayLoading(false)
                view?.displayError(it.message)
            }
        ))
    }

    override fun filter(query: String): List<JobResponse> {
        filtered = jobs.filter {
            it.positionName.contains(query, true)
                    || it.city.contains(query, true)
                    || it.country.contains(query, true)
                    || it.description.contains(query, true)
        }
        return filtered
    }

    override fun setFavorite(jobId: Int): Single<List<JobResponse>> {
        filtered.find { it.id == jobId }?.let {
            it.favorited = !it.favorited
        }

        return Single.just(filtered)
    }

    override fun apply(jobId: Int): Single<List<JobResponse>> {
        filtered.find { it.id == jobId }?.let {
            it.applied = !it.applied
        }

        return Single.just(filtered)
    }
}