package com.suhovey.shoppinglist.domain

class DeleteShopItemUseCase(private val shopItemsRepository: ShopItemsRepository) {

    fun deleteShopItem(id: Int): Boolean {
        return shopItemsRepository.deleteShopItem(id)
    }

}