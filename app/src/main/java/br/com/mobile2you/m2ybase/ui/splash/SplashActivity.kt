package br.com.mobile2you.m2ybase.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.mobile2you.m2ybase.data.local.PreferencesHelper
import br.com.mobile2you.m2ybase.ui.simplelist.createSimpleListIntent

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        if(PreferencesHelper.isLogged)
        startActivity(createSimpleListIntent())

        finish()
    }
}
