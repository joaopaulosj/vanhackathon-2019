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
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.addTextWatcherDebounce
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.notImplementedFeature
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.setup
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.singleSubscribe
import kotlinx.android.synthetic.main.fragment_events.*
import android.content.Intent
import android.provider.CalendarContract


class EventsFragment : Fragment(), EventsAdapter.OnItemClickListener {

    private val adapter by lazy {
        val adapter = EventsAdapter(activity!!, this)
        ViewCompat.setNestedScrollingEnabled(eventsRv, false)
        adapter.setHasStableIds(true)
        eventsRv.setup(adapter)
        adapter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_events, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadEvents()
        setupSearch()
    }

    private fun setupSearch() {
        eventsEt.addTextWatcherDebounce(200) {
            EventsRepository.filter(eventsEt.text.toString()).apply {
                adapter.mList = this
            }
        }
    }

    private fun loadEvents() {
        EventsRepository.getEvents().singleSubscribe(
            onSuccess = {
                adapter.mList = it
            }
        )
    }

    override fun onItemClicked(item: EventResponse) {
        activity?.notImplementedFeature()
    }

    override fun onCalendarClicked(item: EventResponse) {
        val intent = Intent(Intent.ACTION_EDIT).apply {
            type = "vnd.android.cursor.item/event"
            putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, item.getStart())
            putExtra(CalendarContract.EXTRA_EVENT_END_TIME, item.getEnd())
            putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true)
            putExtra(CalendarContract.Events.TITLE, item.name)
            putExtra(CalendarContract.Events.DESCRIPTION, item.subtitle)
            putExtra(CalendarContract.Events.EVENT_LOCATION, "${item.city}, ${item.country}")
        }
        activity?.startActivity(intent)
    }
}
