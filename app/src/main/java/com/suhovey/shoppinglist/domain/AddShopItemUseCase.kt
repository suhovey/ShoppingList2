package com.suhovey.shoppinglist.domain

class AddShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    fun addShopItem(it: ShopItem): Int {
        return shopItemRepository.addShopItem(it)
    }

}