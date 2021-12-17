package com.suhovey.shoppinglist.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.suhovey.shoppinglist.R

class ShopItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById<TextView>(R.id.card_shop_item_name)
    val tvCount = view.findViewById<TextView>(R.id.card_shop_item_count)
}