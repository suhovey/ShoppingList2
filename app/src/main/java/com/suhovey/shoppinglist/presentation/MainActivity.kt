package com.suhovey.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.suhovey.shoppinglist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel : MainShopItemViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this).get(MainShopItemViewModel::class.java) // or can use [MainShopItemViewModel::class.java]
        viewModel.listShopItem.observe(this) {
            Log.d("Test from MainActivity", it.toString())
        }
    }
}