package br.com.joaopaulosj.vanhackathon2019.ui.splash

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import br.com.joaopaulosj.vanhackathon2019.ui.home.createHomeIntent

class SplashActivity : AppCompatActivity() {
	
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		
		Handler().postDelayed({
			startActivity(createHomeIntent())
			finish()
		}, 2000)
		
	}
}
