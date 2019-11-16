package br.com.joaopaulosj.vanhackathon2019.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.joaopaulosj.vanhackathon2019.ui.simplelist.createSimpleListIntent

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if(PreferencesHelper.isLogged)
        startActivity(createSimpleListIntent())

        finish()
    }
}
