package br.com.joaopaulosj.vanhackathon2019.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.joaopaulosj.vanhackathon2019.R
import kotlinx.android.synthetic.main.fragment_events.view.*

class EventsFragment : Fragment() {
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
	                          savedInstanceState: Bundle?): View? {
		val view = inflater.inflate(R.layout.fragment_events, container, false)
		
//		view.webview.apply {
//			settings.javaScriptEnabled = true
//			loadUrl("https://vanhack.com/platform/#/events")
//		}
		
		return view
	}
	
	
}