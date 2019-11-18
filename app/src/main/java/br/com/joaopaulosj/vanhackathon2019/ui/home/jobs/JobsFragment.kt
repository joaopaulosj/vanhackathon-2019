package br.com.joaopaulosj.vanhackathon2019.ui.home.jobs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.JobResponse
import br.com.joaopaulosj.vanhackathon2019.data.repositories.JobsRepository
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.addTextWatcherDebounce
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.notImplementedFeature
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.setup
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.singleSubscribe
import kotlinx.android.synthetic.main.fragment_jobs.*
import org.jetbrains.anko.sdk25.coroutines.textChangedListener
import java.util.concurrent.TimeUnit

class JobsFragment : Fragment(), JobsAdapter.OnItemClickListener {

    private val adapter by lazy {
        val adapter = JobsAdapter(activity!!, this)
        ViewCompat.setNestedScrollingEnabled(jobsRv, false)
        adapter.setHasStableIds(true)
        jobsRv.setup(adapter)
        adapter
    }

    private val filtersAdapter by lazy {
        val adapter = JobFilterAdapter(activity!!)
        jobsFilterRv.setup(
            adapter,
            layoutManager = LinearLayoutManager(activity!!, LinearLayoutManager.HORIZONTAL, false)
        )
        adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_jobs, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadJobs()
        setupSearch()
    }

    private fun loadJobs() {
        JobsRepository.getJobs().singleSubscribe(
            onSuccess = {
                adapter.mList = it
                filtersAdapter.mList = JobsRepository.getCurrentFilters()
            }
        )
    }

    private fun setupSearch() {
        jobsEt.addTextWatcherDebounce(200) {
            JobsRepository.filter(jobsEt.text.toString()).apply {
                adapter.mList = this
            }
        }
    }

    override fun onItemClicked(item: JobResponse) {
        activity?.notImplementedFeature()
    }

    override fun onFavoriteClicked(itemId: Int) {
        JobsRepository.setFavorite(itemId)
            .singleSubscribe(onSuccess = {
                adapter.mList = it
            })
    }

    override fun onApplyClicked(itemId: Int) {
        JobsRepository.apply(itemId).delay(2, TimeUnit.SECONDS)
            .singleSubscribe(onSuccess = {
                adapter.mList = it
            })
    }
}
