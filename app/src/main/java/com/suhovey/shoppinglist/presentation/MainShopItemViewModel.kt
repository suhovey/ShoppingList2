package com.suhovey.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.suhovey.shoppinglist.data.ShopItemRepositoryImpl
import com.suhovey.shoppinglist.domain.*

class MainShopItemViewModel : ViewModel() {

    private val repository = ShopItemRepositoryImpl

    private val getListShopItemUseCase = GetListShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)

    val listShopItem = MutableLiveData<List<ShopItem>>()

    fun getListShopItem() {
        val list = getListShopItemUseCase.getListShopItems()
        listShopItem.postValue(list)
    }

    fun deleteShopItem(id: Int): Boolean {
        if (!deleteShopItemUseCase.deleteShopItem(id)) return false
        getListShopItem()
        return true
    }

    fun changeEnableShopItem(id: Int): Boolean {
        val it = getShopItemUseCase.getShopItem(id) ?: return false
        if (!editShopItemUseCase.editShopItem(ShopItem(it.name, it.count, !it.enabled))) return false
        getListShopItem()
        return true
    }
}