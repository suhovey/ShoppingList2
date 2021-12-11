package com.suhovey.shoppinglist.data

import com.suhovey.shoppinglist.domain.ShopItem
import com.suhovey.shoppinglist.domain.ShopItemRepository

object ShopItemRepositoryImpl : ShopItemRepository {

    private val listShopItem = mutableListOf<ShopItem>()
    private var autoincrementId = 0

    init {
        for (i in 0 until 10) {
            val it = ShopItem("Name ${i+1}", (i+1).toDouble(), true)
            addShopItem(it)
        }
    }

    override fun addShopItem(it: ShopItem): Int {

        if (it.id == ShopItem.UNDEFINED_ID) {
            it.id = autoincrementId++
        }

        listShopItem.add(it)

        return it.id
    }

    override fun deleteShopItem(id: Int): Boolean {

        val it = listShopItem.find { it.id == id } ?: return false

        listShopItem.remove(it)

        return true
    }

    override fun editShopItem(it: ShopItem): Boolean {

        if (!deleteShopItem(it.id)) return false

        addShopItem(it)

        return true

    }

    override fun getListShopItem(): List<ShopItem> {

        return listShopItem.toList()

    }

    override fun getShopItem(id: Int): ShopItem? {

        return listShopItem.find { it.id == id } ?: null

    }
}