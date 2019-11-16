package br.com.mobile2you.m2ybase.ui.simplelist

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.mobile2you.m2ybase.R
import br.com.mobile2you.m2ybase.ui.base.SimpleBaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.item_list.view.*

class SimpleListAdapter(context: Context, private val onItemClickListener: OnItemClickListener) :
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
            holder.bind(mList[position], onItemClickListener)
        }
    }

    override fun getItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        //TODO change layout to inflate
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        return ItemViewHolder(itemView)
    }

    interface OnItemClickListener {
        fun onItemClicked(item: String)
    }

    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: String, onItemClickListener: OnItemClickListener) {
            itemView.apply {
                //TODO bind view
                setOnClickListener {
                    onItemClickListener.onItemClicked(item)
                }
                itemListContenTv.text = item
            }
        }
    }
}