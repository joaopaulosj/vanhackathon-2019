package br.com.joaopaulosj.vanhackathon2019.ui.home.jobs

import br.com.joaopaulosj.vanhackathon2019.data.remote.models.JobResponse
import br.com.joaopaulosj.vanhackathon2019.ui.base.BasePresenter
import br.com.joaopaulosj.vanhackathon2019.ui.base.BaseView
import io.reactivex.Single

interface JobsContract {

    interface View : BaseView<Presenter> {
        fun displayItems(items: List<JobResponse>)
        fun displayFilters(items: List<String>)
        fun displayError(msg: String?)
        fun displayLoading(loading: Boolean)
    }

    interface Presenter : BasePresenter<View> {
        fun loadItems()
        fun filter(query: String): List<JobResponse>
        fun setFavorite(jobId: Int): Single<List<JobResponse>>
        fun apply(jobId: Int): Single<List<JobResponse>>
    }

}