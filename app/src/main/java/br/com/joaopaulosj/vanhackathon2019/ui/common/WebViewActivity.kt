package br.com.joaopaulosj.vanhackathon2019.ui.common

import android.content.Context
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import br.com.joaopaulosj.vanhackathon2019.AppConstants
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_webview.*
import org.jetbrains.anko.intentFor

class WebViewActivity : BaseActivity() {
    private lateinit var title: String
    private lateinit var url: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        getExtras()
        loadWebView()
        setToolbar(title, true)
    }
    
    private fun getExtras() {
        title = intent.getStringExtra(AppConstants.EXTRA_ACTIVITY_TITLE)
        url = intent.getStringExtra(AppConstants.EXTRA_URL)
    }

    private fun loadWebView() {
        webview.webViewClient = object: WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }
        }
        webview.loadUrl(url)
    }
}

fun Context.createWebViewIntent(activityTitle: String, url: String) =
        intentFor<WebViewActivity>(AppConstants.EXTRA_ACTIVITY_TITLE to activityTitle,
                AppConstants.EXTRA_URL to url)
