package com.suhovey.shoppinglist.domain

interface ShopItemRepository {

    fun addShopItem(it: ShopItem): Int

    fun deleteShopItem(id: Int): Boolean

    fun editShopItem(it: ShopItem): Boolean

    fun getListShopItem(): List<ShopItem>

    fun getShopItem(id: Int): ShopItem?

}