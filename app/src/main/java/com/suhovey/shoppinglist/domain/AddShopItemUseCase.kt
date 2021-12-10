package com.suhovey.shoppinglist.domain

class AddShopItemUseCase(private val shopItemsRepository: ShopItemsRepository) {

    fun addShopItem(it: ShopItemData): Int {
        shopItemsRepository.addShopItem(it)
        return -1
    }

}