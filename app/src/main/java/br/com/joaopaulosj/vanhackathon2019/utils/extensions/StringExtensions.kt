package br.com.joaopaulosj.vanhackathon2019.utils.extensions

import android.os.Build
import android.text.Html
import android.widget.TextView

fun String.fromHtml() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
	Html.fromHtml(this, Html.FROM_HTML_MODE_COMPACT)
} else {
	Html.fromHtml(this)
}

fun TextView.setHtmlText(text: String) {
	this.text = text.fromHtml()
}