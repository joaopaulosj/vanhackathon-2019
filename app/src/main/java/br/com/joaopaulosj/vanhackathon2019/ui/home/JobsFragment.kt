package br.com.joaopaulosj.vanhackathon2019.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.JobResponse
import br.com.joaopaulosj.vanhackathon2019.data.repositories.JobsRepository
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.setup
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.singleSubscribe
import kotlinx.android.synthetic.main.fragment_jobs.*

class JobsFragment : Fragment() {
	
	private val adapter by lazy {
		val adapter = JobsAdapter(activity!!, object : JobsAdapter.OnItemClickListener {
			override fun onItemClicked(item: JobResponse) {
			
			}
		})
		jobsRv.setup(adapter)
		adapter
	}
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
	                          savedInstanceState: Bundle?): View? {
		val view = inflater.inflate(R.layout.fragment_jobs, container, false)
		
		loadJobs()
		
		return view
	}
	
	private fun loadJobs() {
		JobsRepository.getJobs().singleSubscribe(
				onSuccess = {
					adapter.mList = it
				},
				onError = {
				
				}
		)
	}
	
	
}
