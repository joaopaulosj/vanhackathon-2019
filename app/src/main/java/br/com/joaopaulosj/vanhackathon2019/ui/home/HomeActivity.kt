package br.com.joaopaulosj.vanhackathon2019.ui.home

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import br.com.joaopaulosj.vanhackathon2019.Constants
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.ui.base.BaseActivity
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.loadCircleImage
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.partial_home_drawer.*
import kotlinx.android.synthetic.main.partial_toolbar.*
import org.jetbrains.anko.intentFor

class HomeActivity : BaseActivity() {
	
	private lateinit var mDrawerToggle: ActionBarDrawerToggle
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_home)
		
		setupDrawerLayout()
	}
	
	private fun setupDrawerLayout() {
		mDrawerToggle = ActionBarDrawerToggle(this, homeDrawerLayout, toolbar,
				R.string.app_name_default, R.string.app_name_default)
		homeDrawerLayout.addDrawerListener(mDrawerToggle)
		
		toolbar_profile_iv.loadCircleImage(Constants.PROFILE_IMG_URL)
	}
	
	override fun onPostCreate(savedInstanceState: Bundle?) {
		super.onPostCreate(savedInstanceState)
		mDrawerToggle.syncState()
	}
}

fun Context.createHomeIntent() = intentFor<HomeActivity>()