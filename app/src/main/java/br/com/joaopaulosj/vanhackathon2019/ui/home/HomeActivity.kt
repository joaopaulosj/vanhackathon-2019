package br.com.joaopaulosj.vanhackathon2019.ui.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import br.com.joaopaulosj.vanhackathon2019.Constants
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.ui.base.BaseActivity
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.loadCircleImage
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.partial_home_content.*
import kotlinx.android.synthetic.main.partial_home_drawer.*
import kotlinx.android.synthetic.main.partial_toolbar.*
import org.jetbrains.anko.intentFor


class HomeActivity : BaseActivity() {
	
	private lateinit var mDrawerToggle: ActionBarDrawerToggle
	private val jobsFragment = JobsFragment()
	private val eventsFragment = EventsFragment()
	private val pipelineFragment = PipelineFragment()
	private val profileFragment = ProfileFragment()
	private var fragmentActive: Fragment = jobsFragment
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_home)
		
		setupDrawerLayout()
		setupBottomNavigation()
	}
	
	private fun setupDrawerLayout() {
		mDrawerToggle = ActionBarDrawerToggle(this, homeDrawerLayout, toolbar,
				R.string.app_name_default, R.string.app_name_default)
		homeDrawerLayout.addDrawerListener(mDrawerToggle)
		
		toolbar_profile_iv.loadCircleImage(Constants.PROFILE_IMG_URL)
		
		homeDrawerSlackBtn.setOnClickListener { openSlack() }
	}
	
	private fun openSlack() {
		val uri = Uri.parse(Constants.SLACK_CHANNEL_URI)
		val intent = Intent(Intent.ACTION_VIEW, uri)
		startActivity(intent)
	}
	
	private fun setupBottomNavigation() {
		val fm = supportFragmentManager
		fm.beginTransaction().add(R.id.homeFragmentContainer, profileFragment, "4").hide(profileFragment).commit()
		fm.beginTransaction().add(R.id.homeFragmentContainer, pipelineFragment, "3").hide(pipelineFragment).commit()
		fm.beginTransaction().add(R.id.homeFragmentContainer, eventsFragment, "2").hide(eventsFragment).commit()
		fm.beginTransaction().add(R.id.homeFragmentContainer, jobsFragment, "1").commit()
		
		bottom_navigation.setOnNavigationItemSelectedListener { item ->
			when (item.itemId) {
				R.id.home_menu_jobs -> {
					showFragment(jobsFragment)
					true
				}
				R.id.home_menu_pipeline -> {
					showFragment(pipelineFragment)
					true
				}
				R.id.home_menu_events -> {
					showFragment(eventsFragment)
					true
				}
				R.id.home_menu_profile -> {
					showFragment(profileFragment)
					true
				}
				else -> {
					false
				}
			}
		}
	}
	
	private fun showFragment(fragment: Fragment) {
		supportFragmentManager.beginTransaction().hide(fragmentActive).show(fragment).commit()
		fragmentActive = fragment
	}
	
	override fun onPostCreate(savedInstanceState: Bundle?) {
		super.onPostCreate(savedInstanceState)
		mDrawerToggle.syncState()
	}
	
}

fun Context.createHomeIntent() = intentFor<HomeActivity>()