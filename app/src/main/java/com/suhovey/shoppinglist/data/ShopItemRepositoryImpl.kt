package com.suhovey.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.suhovey.shoppinglist.domain.ShopItem
import com.suhovey.shoppinglist.domain.ShopItemRepository

object ShopItemRepositoryImpl : ShopItemRepository {

    private val listShopItem = mutableListOf<ShopItem>()
    private var autoincrementId = 0
    private val listShopItemLiveData = MutableLiveData<List<ShopItem>>()

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

        listUpdate()

        return it.id
    }

    override fun deleteShopItem(id: Int): Boolean {

        val it = listShopItem.find { it.id == id } ?: return false

        listShopItem.remove(it)

        listUpdate()

        return true
    }

    override fun editShopItem(it: ShopItem): Boolean {

        if (!deleteShopItem(it.id)) return false

        addShopItem(it)

        return true

    }

    override fun getListShopItem(): LiveData<List<ShopItem>> {

        return listShopItemLiveData

    }

    override fun getShopItem(id: Int): ShopItem? {

        return listShopItem.find { it.id == id } ?: null

    }

    private fun listUpdate() {
        listShopItemLiveData.value = listShopItem.toList()
    }
}