package com.suhovey.shoppinglist.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.suhovey.shoppinglist.R
import com.suhovey.shoppinglist.domain.ShopItem

class MainShopItemAdapter : ListAdapter<ShopItem, MainShopItemAdapter.ShopItemViewHolder>(MainShopItemDiffCallback()) {

    var count = 0

    var onShopItemLongClickListener: ((id: Int) -> Unit)? = null
    var onShopItemClickListener: ((item: ShopItem) -> Unit)? = null

    override fun getItemViewType(position: Int) =
        if (getItem(position).enabled) TYPE_SHOP_ITEM_IS_ENABLED else TYPE_SHOP_ITEM_IS_DISABLED

    class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.card_shop_item_name)
        val tvCount = view.findViewById<TextView>(R.id.card_shop_item_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        Log.d("ShopItemList", "onCreateViewHolder: ${++count}")

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