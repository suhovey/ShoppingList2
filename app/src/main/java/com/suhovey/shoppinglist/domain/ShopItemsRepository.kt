package com.suhovey.shoppinglist.domain

interface ShopItemsRepository {

    fun addShopItem(it: ShopItemData): Int
    fun deleteShopItem(id: Int): Boolean
    fun editShopItem(it: ShopItemData): Boolean
    fun getListShopItems(): List<ShopItemData>
    fun getShopItem(id: Int): ShopItemData

}