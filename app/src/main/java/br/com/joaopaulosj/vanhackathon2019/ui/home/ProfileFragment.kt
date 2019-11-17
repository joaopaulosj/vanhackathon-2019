package br.com.joaopaulosj.vanhackathon2019.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.joaopaulosj.vanhackathon2019.R
import kotlinx.android.synthetic.main.fragment_profile.view.*

class ProfileFragment : Fragment() {
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
	                          savedInstanceState: Bundle?): View? {
		val view = inflater.inflate(R.layout.fragment_profile, container, false)
		
		view.webview.apply {
			settings.javaScriptEnabled = true
			loadUrl("https://vanhack.com/platform/profile")
		}
		
		return view
	}
	
	
}
