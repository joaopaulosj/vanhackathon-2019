package br.com.joaopaulosj.vanhackathon2019.ui.home.jobs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.ui.base.SimpleBaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_job_filter.view.*


class JobFilterAdapter(context: Context) :
		SimpleBaseRecyclerViewAdapter(context) {
	
	var mList: List<String> = ArrayList()
		set(value) {
			field = value
			notifyDataSetChanged()
		}
	
	override val displayableItemsCount: Int
		get() = mList.size
	
	override fun onBindRecyclerViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		if (holder is ItemViewHolder) {
			holder.bind(mList[position])
		}
	}
	
	override fun getItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_job_filter, parent, false)
		return ItemViewHolder(itemView)
	}
	
	inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(item: String) {
			itemView.apply {
				itemJobFilterTv.text = item
			}
		}
	}
}