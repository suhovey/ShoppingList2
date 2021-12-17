package com.suhovey.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.suhovey.shoppinglist.domain.ShopItem
import com.suhovey.shoppinglist.domain.ShopItemRepository
import kotlin.random.Random

object ShopItemRepositoryImpl : ShopItemRepository {

    // private val listShopItem = sortedSetOf(Comparator<ShopItem> { o1, o2 -> o1.id.compareTo(o2.id) })
    private val listShopItem = sortedSetOf(object : Comparator<ShopItem> {
        override fun compare(o1: ShopItem, o2: ShopItem): Int {
            return o1.id.compareTo(o2.id)
        }
    })

    private var autoincrementId = 0
    private val listShopItemLiveData = MutableLiveData<List<ShopItem>>()

    init {
        for (i in 0 until 1000) {
            val it = ShopItem("Name ${i + 1}", (i + 1).toDouble(), Random.nextBoolean())
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