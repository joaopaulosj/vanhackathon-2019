package br.com.joaopaulosj.vanhackathon2019.ui.home.events


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.EventResponse
import br.com.joaopaulosj.vanhackathon2019.data.repositories.EventsRepository
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.setup
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.singleSubscribe
import kotlinx.android.synthetic.main.fragment_events.*

class EventsFragment : Fragment(), EventsAdapter.OnItemClickListener {
	
	private val adapter by lazy {
		val adapter = EventsAdapter(activity!!, this)
		ViewCompat.setNestedScrollingEnabled(eventsRv, false)
		adapter.setHasStableIds(true)
		eventsRv.setup(adapter)
		adapter
	}
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
	                          savedInstanceState: Bundle?): View? {
		val view = inflater.inflate(R.layout.fragment_events, container, false)
		
		loadEvents()
		
		return view
	}
	
	private fun loadEvents() {
		EventsRepository.getEvents().singleSubscribe(
				onSuccess = {
					adapter.mList = it
//					filtersAdapter.mList = JobsRepository.getCurrentFilters()
				}
		)
	}
	
	override fun onItemClicked(item: EventResponse) {
	
	}
}
