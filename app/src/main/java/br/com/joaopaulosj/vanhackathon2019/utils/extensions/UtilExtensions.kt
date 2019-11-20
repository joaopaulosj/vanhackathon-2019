package br.com.joaopaulosj.vanhackathon2019.utils.extensions

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.appcompat.content.res.AppCompatResources
import br.com.joaopaulosj.vanhackathon2019.AppConstants
import java.io.File
import java.util.*


fun Context.getColorRes(idRes: Int): Int {
    return ContextCompat.getColor(this, idRes)
}

@Deprecated("Use makeCall() from anko instead")
fun Context.callPhone(phone: String): Boolean {
    try {
        val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null))
        startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
        return false
    }
    return true
}