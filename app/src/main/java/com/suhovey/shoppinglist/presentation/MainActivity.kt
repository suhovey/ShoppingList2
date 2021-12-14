package com.suhovey.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.suhovey.shoppinglist.R
import com.suhovey.shoppinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainShopItemViewModel
    private lateinit var llListShopItem: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        llListShopItem = findViewById(R.id.ll_list_shop_item)

        viewModel =
            ViewModelProvider(this).get(MainShopItemViewModel::class.java) // or can use [MainShopItemViewModel::class.java]
        viewModel.listShopItem.observe(this) {
            //Log.d("Test from MainActivity", it.toString())
            showListShopItem(it)
        }
    }

    private fun showListShopItem(list: List<ShopItem>) {
        llListShopItem.removeAllViews()
        for (item in list) {
            val resource = if (item.enabled) { R.layout.shop_item_enabled } else { R.layout.shop_item_disabled }
            val view = LayoutInflater.from(this).inflate(resource, llListShopItem, false)
            view.findViewById<TextView>(R.id.card_shop_item_name).text = item.name
            view.findViewById<TextView>(R.id.card_shop_item_count).text = item.count.toString()
            llListShopItem.addView(view)

            view.setOnLongClickListener {
                viewModel.changeEnableShopItem(item.id)
                return@setOnLongClickListener true
            }

        }
    }
}