package com.suhovey.shoppinglist.presentation

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.suhovey.shoppinglist.R
import com.suhovey.shoppinglist.domain.ShopItem

class MainShopItemAdapter : RecyclerView.Adapter<MainShopItemAdapter.ShopItemViewHolder>() {

    var countInCreateViewHolder = 0
    var countInBindViewHolder = 0

    var onShopItemLongClickListener: ((id: Int) -> Unit)? = null
    var onShopItemClickListener: ((item: ShopItem) -> Unit)? = null

    var listShopItem = listOf<ShopItem>()
        set(value) {
            val callbackDiff = MainShopItemDiffCallback(listShopItem, value)
            val diffResult = DiffUtil.calculateDiff(callbackDiff)
            diffResult.dispatchUpdatesTo(this)
            field = value
        }

    override fun getItemViewType(position: Int) =
        if (listShopItem[position].enabled) TYPE_SHOP_ITEM_IS_ENABLED else TYPE_SHOP_ITEM_IS_DISABLED

    class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.card_shop_item_name)
        val tvCount = view.findViewById<TextView>(R.id.card_shop_item_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {

        Log.d("ShopItemList", "onCreateViewHolder: ${++countInCreateViewHolder}")

        val layout = when (viewType) {
            TYPE_SHOP_ITEM_IS_ENABLED -> R.layout.shop_item_enabled
            TYPE_SHOP_ITEM_IS_DISABLED -> R.layout.shop_item_disabled
            else -> throw RuntimeException("Unknown view type: $viewType")
        }

        val view = LayoutInflater.from(parent.context).inflate(layout, parent, false)

        return ShopItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {

        Log.d("ShopItemList", "onBindViewHolder: ${++countInBindViewHolder}")

        val item = listShopItem[position]

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

    override fun getItemCount(): Int {
        return listShopItem.size
    }

    companion object {
        const val TYPE_SHOP_ITEM_IS_ENABLED = 1
        const val TYPE_SHOP_ITEM_IS_DISABLED = 0

        const val MAX_SIZE_POOL = 30
    }
}