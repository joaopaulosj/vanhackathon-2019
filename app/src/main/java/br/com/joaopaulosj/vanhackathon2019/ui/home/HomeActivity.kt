package br.com.joaopaulosj.vanhackathon2019.ui.home

import android.content.Context
import android.os.Bundle
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.ui.base.BaseActivity
import org.jetbrains.anko.intentFor

class HomeActivity : BaseActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_home)
	}
}

fun Context.createHomeIntent() = intentFor<HomeActivity>()