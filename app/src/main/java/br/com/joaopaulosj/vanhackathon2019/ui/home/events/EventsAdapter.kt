package br.com.joaopaulosj.vanhackathon2019.ui.home.events

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.EventResponse
import br.com.joaopaulosj.vanhackathon2019.ui.base.SimpleBaseRecyclerViewAdapter
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.loadDrawable
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.loadImage
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.setHtmlText
import kotlinx.android.synthetic.main.item_event.view.*


class EventsAdapter(context: Context, private val onItemClickListener: OnItemClickListener) :
		SimpleBaseRecyclerViewAdapter(context) {
	
	var mList: List<EventResponse> = ArrayList()
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
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
		return ItemViewHolder(itemView)
	}
	
	interface OnItemClickListener {
		fun onItemClicked(item: EventResponse)
	}
	
	inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(item: EventResponse, onItemClickListener: OnItemClickListener) {
			itemView.apply {
				setOnClickListener {
					onItemClickListener.onItemClicked(item)
				}
				
				if (item.applied) {
					itemEventButtonTv.text = "SEE APPLICATION"
					itemEventButtonTv.setTextColor(ContextCompat.getColor(context, R.color.colorSuccess))
				} else {
					itemEventButtonTv.text = "SEE DETAILS"
					itemEventButtonTv.setTextColor(ContextCompat.getColor(context, R.color.colorMain))
				}
				
				itemEventCoverIv.loadImage(item.cover)
				itemEventTitleTv.text = item.name
				itemEventFlagIv.loadDrawable(item.getFlagResName())
				itemEventCityTv.text = item.city
				itemEventDateTv.text = item.getDate()
				itemEventDeadlineTv.setHtmlText(item.getDeadline())
			}
		}
	}
}