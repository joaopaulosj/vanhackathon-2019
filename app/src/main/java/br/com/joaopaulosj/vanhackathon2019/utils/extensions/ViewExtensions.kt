package br.com.joaopaulosj.vanhackathon2019.utils.extensions

import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.utils.CustomLinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.google.android.material.snackbar.Snackbar
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import java.util.concurrent.TimeUnit


fun dpToPx(dp: Float): Int {
	val density = Resources.getSystem().displayMetrics.density
	return Math.round(dp * density)
}

fun View.setVisible(visible: Boolean, useInvisible: Boolean = false) {
	visibility = when {
		visible -> View.VISIBLE
		useInvisible -> View.INVISIBLE
		else -> View.GONE
	}
}

fun ImageView.loadImage(url: String?, options: RequestOptions? = null, simpleTarget: SimpleTarget<Drawable>? = null): Boolean {
	try {
		val optionsToApply = options ?: RequestOptions()
				.placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder_photo))
				.centerCrop()
		if (simpleTarget == null) {
			Glide.with(context).load(url).apply(optionsToApply).into(this)
		} else {
			Glide.with(context).load(url).apply(optionsToApply).into(simpleTarget)
		}
	} catch (e: Exception) {
		e.printStackTrace()
		return false
	}
	return true
}

fun ImageView.loadDrawable(filename: String?): Boolean {
	try {
		Glide.with(context)
				.load(resources.getIdentifier(filename, "drawable", context.packageName))
				.into(this)
	} catch (e: Exception) {
		e.printStackTrace()
		return false
	}
	return true
}

fun ImageView.loadCircleImage(url: String?, addPlaceholder: Boolean = false): Boolean {
	try {
		val optionsToApply = RequestOptions()
		if (addPlaceholder) optionsToApply.placeholder(ContextCompat.getDrawable(context, R.drawable.placeholder_user))
		optionsToApply.circleCrop()
		Glide.with(context).load(url).apply(optionsToApply).into(this)
	} catch (e: Exception) {
		e.printStackTrace()
		return false
	}
	return true
}

fun RecyclerView.setup(adapter: RecyclerView.Adapter<in RecyclerView.ViewHolder>,
                       layoutManager: RecyclerView.LayoutManager? = CustomLinearLayoutManager(this.context),
                       decoration: RecyclerView.ItemDecoration? = null,
                       hasFixedSize: Boolean = true) {
	
	this.adapter = adapter
	this.layoutManager = layoutManager
	this.setHasFixedSize(hasFixedSize)
	decoration?.let { this.addItemDecoration(it) }
}

//SNACK
fun showSnack(coordinator: CoordinatorLayout, message: String, retryText: String, action: (v: View) -> Unit?, indefinite: Boolean = true) {
	Snackbar.make(coordinator, message, if (indefinite) Snackbar.LENGTH_INDEFINITE else Snackbar.LENGTH_LONG)
			.setAction(retryText) { v -> action(v) }
			.show()
}

fun EditText.addTextWatcherDebounce(timeoutInMillis: Long, action: ((String) -> Unit)) {
	Observable.create(ObservableOnSubscribe<String> { subscriber ->
		addTextChangedListener(object : TextWatcher {
			override fun afterTextChanged(s: Editable?) {}
			
			override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
			
			override fun onTextChanged(query: CharSequence?, start: Int, before: Int, count: Int) {
				subscriber.onNext(query.toString())
			}
		})
	}).debounce(timeoutInMillis, TimeUnit.MILLISECONDS).distinct()
			.observableSubscribe(onNext = {
				action(it)
			})
}