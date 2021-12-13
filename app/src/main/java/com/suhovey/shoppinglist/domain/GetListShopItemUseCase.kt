package com.suhovey.shoppinglist.domain

import androidx.lifecycle.LiveData

class GetListShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    fun getListShopItems(): LiveData<List<ShopItem>> {

        return shopItemRepository.getListShopItem()

    }

}