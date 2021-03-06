package com.suhovey.shoppinglist.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.suhovey.shoppinglist.R
import com.suhovey.shoppinglist.domain.ShopItem

class MainShopItemAdapter : ListAdapter<ShopItem, ShopItemViewHolder>(MainShopItemDiffCallback()) {

    var onShopItemLongClickListener: ((id: Int) -> Unit)? = null
    var onShopItemClickListener: ((item: ShopItem) -> Unit)? = null

    override fun getItemViewType(position: Int) =
        if (getItem(position).enabled) TYPE_SHOP_ITEM_IS_ENABLED else TYPE_SHOP_ITEM_IS_DISABLED

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        val layout = when (viewType) {
            TYPE_SHOP_ITEM_IS_ENABLED -> R.layout.shop_item_enabled
            TYPE_SHOP_ITEM_IS_DISABLED -> R.layout.shop_item_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)

        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        val item = getItem(position)

        holder.tvName.text = item.name
        holder.tvCount.text = item.count.toString()

        holder.itemView.setOnLongClickListener {
            onShopItemLongClickListener?.invoke(item.id)
            true
        }

        holder.itemView.setOnClickListener {
            onShopItemClickListener?.invoke(item)
        }

    }

    companion object {
        const val TYPE_SHOP_ITEM_IS_ENABLED = 1
        const val TYPE_SHOP_ITEM_IS_DISABLED = 0

        const val MAX_SIZE_POOL = 30
    }
}