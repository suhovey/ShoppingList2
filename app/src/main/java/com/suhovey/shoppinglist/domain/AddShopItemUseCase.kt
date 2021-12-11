package com.suhovey.shoppinglist.domain

class AddShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    fun addShopItem(it: ShopItem): Int {
        shopItemRepository.addShopItem(it)
        return -1
    }

}