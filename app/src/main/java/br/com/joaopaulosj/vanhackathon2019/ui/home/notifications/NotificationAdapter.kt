package br.com.joaopaulosj.vanhackathon2019.ui.home.notifications

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.NotificationResponse
import br.com.joaopaulosj.vanhackathon2019.ui.base.SimpleBaseRecyclerViewAdapter
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.displayName
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.setVisible
import kotlinx.android.synthetic.main.item_notification.view.*

class NotificationAdapter(context: Context, private val onItemClickListener: OnItemClickListener) :
		SimpleBaseRecyclerViewAdapter(context) {
	
	var mList: List<NotificationResponse> = ArrayList()
		set(value) {
			field = value
			notifyDataSetChanged()
		}
	
	override val displayableItemsCount: Int
		get() = mList.size
	
	override fun onBindRecyclerViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		if (holder is ItemViewHolder) {
			holder.bind(mList[position], onItemClickListener)
		}
	}
	
	override fun getItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_notification, parent, false)
		return ItemViewHolder(itemView)
	}
	
	interface OnItemClickListener {
		fun onItemClicked(item: NotificationResponse)
	}
	
	inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(item: NotificationResponse, onItemClickListener: OnItemClickListener) {
			itemView.apply {
				setOnClickListener {
					onItemClickListener.onItemClicked(item)
				}
				
				itemNotiTitleTv.text = item.title
				itemNotiDescTv.text = item.description
				itemNotiDateTv.text = item.getDate()?.displayName("MM/dd/yyyy")
				itemNotiDotIv.setVisible(item.isRead.not())
			}
		}
	}
}