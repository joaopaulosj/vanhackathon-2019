package br.com.joaopaulosj.vanhackathon2019.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.JobResponse
import br.com.joaopaulosj.vanhackathon2019.ui.base.SimpleBaseRecyclerViewAdapter
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.loadDrawable
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.setHtmlText
import kotlinx.android.synthetic.main.item_job.view.*

class JobsAdapter(context: Context, private val onItemClickListener: OnItemClickListener) :
		SimpleBaseRecyclerViewAdapter(context) {
	
	var mList: List<JobResponse> = ArrayList()
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
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_job, parent, false)
		return ItemViewHolder(itemView)
	}
	
	interface OnItemClickListener {
		fun onItemClicked(item: JobResponse)
	}
	
	inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(item: JobResponse, onItemClickListener: OnItemClickListener) {
			itemView.apply {
				setOnClickListener {
					onItemClickListener.onItemClicked(item)
				}
				jobTitleTv.text = item.positionName.trim()
				jobDescriptionTv.setHtmlText(item.description)
				jobCityTv.text = item.city
				jobFlagIv.loadDrawable(item.getFlagResName())
			}
		}
	}
}