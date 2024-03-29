package br.com.joaopaulosj.vanhackathon2019.utils.extensions

import android.app.Activity
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Handler
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import br.com.joaopaulosj.vanhackathon2019.R
import org.jetbrains.anko.longToast
import java.io.Serializable

fun Context.isNetworkConnected(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting
}

fun Activity.startActivitySlideTransition(intent: Intent, requestCode: Int? = null) {
    startActivityTransition(intent, R.anim.anim_close_scale, R.anim.slide_in_left, 1, requestCode)
}

fun Activity.startActivityTransition(
    intent: Intent, idAnimationOut: Int,
    idAnimationIn: Int, delay: Long, requestCode: Int? = null
) {
    if (requestCode == null) {
        Handler().postDelayed({
            this.startActivity(intent)
            this.overridePendingTransition(idAnimationIn, idAnimationOut)
        }, delay)
    } else {
        Handler().postDelayed({
            this.startActivityForResult(intent, requestCode)
            this.overridePendingTransition(idAnimationIn, idAnimationOut)
        }, delay)
    }
}

fun Activity.finishWithSlideTransition() {
    finish()
    overridePendingTransition(R.anim.anim_open_scale, R.anim.slide_out_right)
}


fun <T : Serializable> Activity.getSerializable(key: String): T {
    return intent.getSerializableExtra(key) as T
}

//TOAST METHODS
fun Context.showToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Context.showLongToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
}

fun Context.showErrorToast(msg: String?) {
    showLongToast(msg ?: getString(R.string.placeholder_error_label))
}

fun Context.notImplementedFeature() {
    longToast("Feature not implemented")
}