package br.com.mobile2you.m2ybase

import android.app.Application
import br.com.mobile2you.m2ybase.data.local.PreferencesHelper
import br.com.mobile2you.m2ybase.data.remote.UserUnauthorizedBus
import com.crashlytics.android.Crashlytics
import io.fabric.sdk.android.Fabric
import io.reactivex.observers.DisposableObserver

class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        setFabric()

        setPreferencesHelper()

        setBus()
    }

    private fun setPreferencesHelper() {
        PreferencesHelper.init(applicationContext)
    }

    private fun setFabric() {
        if (BuildConfig.REPORT_CRASH) {
            Fabric.with(this, Crashlytics())
        }
    }

    private fun setBus() {
        UserUnauthorizedBus.getEvents().subscribeWith(object : DisposableObserver<Any>() {
            override fun onNext(o: Any) {
                startAuthenticationActivity()
            }

            override fun onError(e: Throwable) {

            }

            override fun onComplete() {

            }
        })
    }

    private fun startAuthenticationActivity() {
        //todo
        //        startActivity(createLoginActivity(true);
    }

}
