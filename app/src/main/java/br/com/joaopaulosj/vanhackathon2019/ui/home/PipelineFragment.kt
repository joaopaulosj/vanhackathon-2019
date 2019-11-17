package br.com.joaopaulosj.vanhackathon2019.ui.home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.joaopaulosj.vanhackathon2019.R

class PipelineFragment : Fragment() {
	
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
	                          savedInstanceState: Bundle?): View? {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_pipeline, container, false)
	}
	
	
}
