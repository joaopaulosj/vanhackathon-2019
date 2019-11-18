package br.com.joaopaulosj.vanhackathon2019.ui.simplelist

import android.content.Context
import android.os.Bundle
import android.util.Log
import br.com.joaopaulosj.vanhackathon2019.AppConstants
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.*
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_swiped_recycler_view.*
import org.jetbrains.anko.*

class SimpleListActivity : BaseActivity(), SimpleListContract.View {

    private val presenter: SimpleListContract.Presenter by lazy {
        val presenter = SimpleListPresenter()
        presenter.attachView(this)
        presenter
    }

    private val adapter by lazy {
        SimpleListAdapter(this,
                object : SimpleListAdapter.OnItemClickListener {
                    override fun onItemClicked(item: String) {
                        //todo implement view click
//                        startActivitySlideTransition(createLoadingActivityIntent())
                    }
                })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_swiped_recycler_view)

        //todo set activity title here
        setToolbar(str(R.string.simple_list_title), false)

        setRecyclerView()

        presenter.loadItems()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun displayItems(items: List<String>) {
        adapter.mList = items
    }

    override fun displayError(msg: String?) {
        Log.e(TAG, str(R.string.snack_error_msg))
        showSnack(swipedRecyclerViewCoordinator,
                msg ?: str(R.string.snack_error_msg),
                str(R.string.snack_error_try_again_msg), {
                presenter.loadItems()
        })
    }

    private fun setRecyclerView() {
        swiperefresh.setOnRefreshListener { presenter.loadItems() }
        recyclerview.setup(adapter)
    }

    override fun displayLoading(loading: Boolean) {
        swiperefresh.isRefreshing = loading
    }

    override fun onBackPressed() {
        super.onBackPressed()
        //todo finish with animation if needed
//        finishWithSlideTransition()
    }
}

//todo change the name of this method according to the name of the activity
fun Context.createSimpleListIntent(extra: String? = null) =
        intentFor<SimpleListActivity>(AppConstants.EXTRA_URL to extra)
