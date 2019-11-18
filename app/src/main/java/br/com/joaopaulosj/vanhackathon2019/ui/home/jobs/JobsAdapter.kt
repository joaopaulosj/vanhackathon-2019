package br.com.joaopaulosj.vanhackathon2019.ui.home.jobs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.JobResponse
import br.com.joaopaulosj.vanhackathon2019.ui.base.SimpleBaseRecyclerViewAdapter
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.dpToPx
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.loadDrawable
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.setHtmlText
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.setup
import kotlinx.android.synthetic.main.item_job.view.*
import kotlin.math.ceil


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
		fun onFavoriteClicked(itemId: Int)
		fun onApplyClicked(itemId: Int)
	}
	
	inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(item: JobResponse, onItemClickListener: OnItemClickListener) {
			itemView.apply {
				setOnClickListener {
					onItemClickListener.onItemClicked(item)
				}
				jobApplyBtn.setLoading(false)
				if (item.applied) {
					jobApplyBtn.setText("APPLIED")
					jobApplyBtn.setBackgroundColor(ContextCompat.getColor(context, R.color.colorSuccess))
				} else {
					jobApplyBtn.setText("APPLY")
					jobApplyBtn.setBackgroundColor(ContextCompat.getColor(context, R.color.colorMain))
				}
				
				jobTitleTv.text = item.positionName.trim()
				jobDescriptionTv.setHtmlText(item.description)
				jobDescriptionTv.setOnClickListener {
					if (jobDescriptionTv.maxLines == 4)
						jobDescriptionTv.maxLines = Integer.MAX_VALUE
					else
						jobDescriptionTv.maxLines = 4
				}
				jobCityTv.text = item.city
				jobFlagIv.loadDrawable(item.getFlagResName())
				
				jobStartIv.setOnClickListener {
					onItemClickListener.onFavoriteClicked(item.id)
				}
				
				jobStartIv.setImageResource(if (item.favorited) {
					R.drawable.ic_star_filled
				} else {
					R.drawable.ic_star_empty
				})
				
				jobApplyBtn.setOnClickListener {
					jobApplyBtn.setLoading(true)
					onItemClickListener.onApplyClicked(item.id)
				}
				
				itemView.post {
					val list = item.getAllSkills()
					val size = list.sumBy { dpToPx(it.name.length * 8f + 25) }
					var span = ceil(size.toDouble() / itemView.width).toInt()
					if (span <= 0) span = 1
					
					val adapter = SkillsAdapter(context)
					val layoutManager = StaggeredGridLayoutManager(span, LinearLayout.HORIZONTAL)
					jobMustHaveRv.setup(adapter, layoutManager = layoutManager)
					adapter.mList = list
				}
			}
		}
	}
}