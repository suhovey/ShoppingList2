package com.suhovey.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import com.suhovey.shoppinglist.data.ShopItemRepositoryImpl
import com.suhovey.shoppinglist.domain.*

class MainShopItemViewModel : ViewModel() {

    private val repository = ShopItemRepositoryImpl

    private val getListShopItemUseCase = GetListShopItemUseCase(repository)
    private val deleteShopItemUseCase = DeleteShopItemUseCase(repository)
    private val editShopItemUseCase = EditShopItemUseCase(repository)
    private val getShopItemUseCase = GetShopItemUseCase(repository)

    val listShopItem = getListShopItemUseCase.getListShopItems()

    fun deleteShopItem(id: Int): Boolean {
        return deleteShopItemUseCase.deleteShopItem(id)
    }

    fun changeEnableShopItem(id: Int): Boolean {
        val it = getShopItemUseCase.getShopItem(id) ?: return false
        return editShopItemUseCase.editShopItem(ShopItem(it.name, it.count, !it.enabled, it.id))
    }
}