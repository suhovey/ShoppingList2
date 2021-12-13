package com.suhovey.shoppinglist.domain

import androidx.lifecycle.LiveData

interface ShopItemRepository {

    fun addShopItem(it: ShopItem): Int

    fun deleteShopItem(id: Int): Boolean

    fun editShopItem(it: ShopItem): Boolean

    fun getListShopItem(): LiveData<List<ShopItem>>

    fun getShopItem(id: Int): ShopItem?

}