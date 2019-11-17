package br.com.joaopaulosj.vanhackathon2019.ui.home.jobs

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import br.com.joaopaulosj.vanhackathon2019.R
import br.com.joaopaulosj.vanhackathon2019.data.remote.models.SkillResponse
import br.com.joaopaulosj.vanhackathon2019.ui.base.SimpleBaseRecyclerViewAdapter
import br.com.joaopaulosj.vanhackathon2019.utils.extensions.getColorRes
import kotlinx.android.synthetic.main.item_must_have_skill.view.*
import org.jetbrains.anko.backgroundResource

class SkillsAdapter(context: Context) : SimpleBaseRecyclerViewAdapter(context) {
	
	var mList: List<SkillResponse> = ArrayList()
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
		val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_must_have_skill, parent, false)
		return ItemViewHolder(itemView)
	}
	
	inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		fun bind(item: SkillResponse) {
			itemView.apply {
				itemView.backgroundResource = if (item.mustHave) {
					R.drawable.bg_stroke_must_have_skill
				} else {
					R.drawable.bg_stroke_nice_to_have_skill
				}
				itemSkillTv.text = item.name
				itemSkillTv.setTextColor(if (item.mustHave) {
					context.getColorRes(R.color.colorWarning)
				} else {
					context.getColorRes(R.color.colorPrimary)
				})
				itemSkillsDotIv.isVisible = item.match
			}
		}
	}
}