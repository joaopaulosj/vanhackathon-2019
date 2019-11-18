package br.com.joaopaulosj.vanhackathon2019.ui.home.notifications

import android.content.Context
import android.os.Bundle
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.NotificationResponse
import br.com.joaopaulosj.vanhackathon2019.ui.base.BaseActivity
import br.com.joaopaulosj.vanhackathon2019.ui.widget.SimpleDividerItemDecoration
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.finishWithSlideTransition
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.notImplementedFeature
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.setup
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.showSnack
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.str
import kotlinx.android.synthetic.main.activity_swiped_recycler_view.*
import org.jetbrains.anko.intentFor

class NotificationsActivity : BaseActivity(), NotificationsContract.View, NotificationAdapter.OnItemClickListener {

    private val presenter: NotificationsContract.Presenter by lazy {
        val presenter = NotificationsPresenter()
        presenter.attachView(this)
        presenter
    }

    private val adapter by lazy {
        NotificationAdapter(this, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swiped_recycler_view)

        setToolbar(getString(R.string.notifications_title), true)

        setRecyclerView()

        presenter.loadItems()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun displayItems(items: List<NotificationResponse>) {
        adapter.mList = items
    }

    override fun onItemClicked(item: NotificationResponse) {
        notImplementedFeature()
    }

    override fun displayError(msg: String?) {
        showSnack(swipedRecyclerViewCoordinator,
            msg ?: str(R.string.snack_error_msg),
            getString(R.string.snack_error_try_again_msg), {
                presenter.loadItems()
            })
    }

    private fun setRecyclerView() {
        swiperefresh.setOnRefreshListener { presenter.loadItems() }
        recyclerview.setup(adapter, decoration = SimpleDividerItemDecoration(this))
    }

    override fun displayLoading(loading: Boolean) {
        swiperefresh.isRefreshing = loading
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishWithSlideTransition()
    }
}

fun Context.createNotificationsIntent() = intentFor<NotificationsActivity>()