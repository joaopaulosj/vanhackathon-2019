package br.com.joaopaulosj.vanhackathon2019.ui.base

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.joaopaulosj.vanhackathon2019.R
import kotlinx.android.synthetic.main.placeholder_default_general.view.*


private const val TYPE_HEADER = 0
private const val TYPE_ITEM = 1
private const val TYPE_EMPTY = 3
private const val TYPE_INVISIBLE = 4
private const val TYPE_FOOTER = 5

private const val HEADER_SIZE = 1
private const val FOOTER_SIZE = 1
private const val PLACE_HOLDER_SIZE = 1

abstract class SimpleBaseRecyclerViewAdapter(val context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    //FLAGS
    private val mAlwaysShowFooter = false
    private val mAlwaysShowHeader = false

    abstract val displayableItemsCount: Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_INVISIBLE -> getInvisibleViewHolder(parent)
            TYPE_EMPTY -> getEmptyViewHolder(parent)
            TYPE_HEADER -> getHeaderViewHolder(parent)!!
            TYPE_FOOTER -> getFooterViewHolder(parent)!!
            else -> getItemViewHolder(parent)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (displayableItemsCount == 0 && isPlaceHolderPosition(position)) {
            TYPE_EMPTY
        } else if (hasHeader() && isPositionHeader(position)) {
            TYPE_HEADER
        } else if (hasFooter() && isPositionFooter(position)) {
            TYPE_FOOTER
        } else {
            TYPE_ITEM
        }
    }

    private fun isPlaceHolderPosition(position: Int): Boolean {
        return hasHeader() && mAlwaysShowHeader && position == 1 ||
                !hasHeader() && position == 0 ||
                !mAlwaysShowHeader && position == 0
    }

    override fun getItemCount(): Int {
        return if (displayableItemsCount == 0) {
            PLACE_HOLDER_SIZE + (if (mAlwaysShowHeader) HEADER_SIZE else 0) + if (mAlwaysShowFooter) FOOTER_SIZE else 0
        } else {
            displayableItemsCount + (if (hasFooter()) FOOTER_SIZE else 0) + if (hasHeader()) HEADER_SIZE else 0
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindRecyclerViewHolder(holder, getRealPosition(position))
        if (holder is PlaceHolderVH) {
            if (holder.getItemViewType() == TYPE_EMPTY) {
                holder.bind(getEmptyLabel(context), getEmptyIcon(context), null)
            }
        }
    }

    abstract fun onBindRecyclerViewHolder(holder: RecyclerView.ViewHolder, position: Int)

    //ITEM METHODS
    protected abstract fun getItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder

    //FOOTER METHODS
    open fun getFooterViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder? {
        return null
    }

    //HEADER METHODS
    open fun getHeaderViewHolder(parent: ViewGroup?): RecyclerView.ViewHolder? {
        return null
    }

    //EMPTY METHODS
    private fun getEmptyLabel(context: Context): String {
        return context.getString(R.string.placeholder_empty_label)
    }

    private fun getEmptyIcon(context: Context): Drawable? {
        return ContextCompat.getDrawable(context, R.drawable.ic_default_empty)
    }

    open fun getEmptyViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val sectionView = LayoutInflater.from(parent.context).inflate(R.layout.placeholder_default_general, parent, false)
        return PlaceHolderVH(sectionView)
    }

    open fun getInvisibleViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val sectionView = LayoutInflater.from(parent.context).inflate(R.layout.empty_layout, parent, false)
        return EmptyPlaceHolderVH(sectionView)
    }

    //GETTERS
    private fun getRealPosition(position: Int): Int {
        return position - if (hasHeader()) HEADER_SIZE else 0
    }

    private fun hasHeader(): Boolean {
        return getHeaderViewHolder(null) != null
    }

    private fun hasFooter(): Boolean {
        return getFooterViewHolder(null) != null
    }

    private fun isPositionHeader(position: Int): Boolean {
        return position == 0
    }

    private fun isPositionFooter(position: Int): Boolean {
        return position == itemCount - 1
    }

    //VIEW HOLDERS
    inner class EmptyPlaceHolderVH(itemView: View) : RecyclerView.ViewHolder(itemView)

    inner class PlaceHolderVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(emptyMsg: String, drawable: Drawable?, refreshClickListener: View.OnClickListener?) {
            itemView.apply {
                itemDefaultLabelTv.text = emptyMsg
                if (drawable == null) {
                    itemDefaultIconIv.visibility = View.GONE
                } else {
                    val tintedDrawable = drawable.mutate()
                    tintedDrawable.setColorFilter(ContextCompat.getColor(itemView.context, R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP)
                    itemDefaultIconIv.setImageDrawable(tintedDrawable)
                }
                if (refreshClickListener != null) {
                    itemDefaultBtn.visibility = View.VISIBLE
                    itemDefaultBtn.setOnClickListener(refreshClickListener)
                }
            }
        }
    }
}