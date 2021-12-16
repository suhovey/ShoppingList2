package com.suhovey.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.suhovey.shoppinglist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainShopItemViewModel
    private lateinit var shopItemAdapter: MainShopItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupRecyclerView()

        viewModel =
            ViewModelProvider(this).get(MainShopItemViewModel::class.java) // or can use [MainShopItemViewModel::class.java]

        viewModel.listShopItem.observe(this) {
            //Log.d("Test from MainActivity", it.toString())
            shopItemAdapter.listShopItem = it
        }
    }

    private fun setupRecyclerView() {
        val rvListShopItem = findViewById<RecyclerView>(R.id.rv_list_shop_item)
        shopItemAdapter = MainShopItemAdapter()
        with(rvListShopItem) {
            adapter = shopItemAdapter
            recycledViewPool.setMaxRecycledViews(
                MainShopItemAdapter.TYPE_SHOP_ITEM_IS_ENABLED,
                MainShopItemAdapter.MAX_SIZE_POOL
            )
            recycledViewPool.setMaxRecycledViews(
                MainShopItemAdapter.TYPE_SHOP_ITEM_IS_DISABLED,
                MainShopItemAdapter.MAX_SIZE_POOL
            )
        }

        setLongClickLestener()

        setClickListener()

        setTouchListener(rvListShopItem)
    }

    private fun setTouchListener(rvListShopItem: RecyclerView) {
        val callbackItemTouch = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val id = shopItemAdapter.listShopItem[viewHolder.adapterPosition].id
                viewModel.deleteShopItem(id)
            }

        }

        val itemTouchHelper = ItemTouchHelper(callbackItemTouch)
        itemTouchHelper.attachToRecyclerView(rvListShopItem)
    }

    private fun setClickListener() {
        shopItemAdapter.onShopItemClickListener = {
            Log.d("ShopItemList", it.toString())
        }
    }

    private fun setLongClickLestener() {
        shopItemAdapter.onShopItemLongClickListener = {
            viewModel.changeEnableShopItem(it)
        }
    }
}