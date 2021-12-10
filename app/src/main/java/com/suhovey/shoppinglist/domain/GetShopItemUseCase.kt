package com.suhovey.shoppinglist.domain

class GetShopItemUseCase(private val shopItemsRepository: ShopItemsRepository) {

    fun getShopItem(id: Int): ShopItemData {
        return shopItemsRepository.getShopItem(id)
    }

}