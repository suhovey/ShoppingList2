package com.suhovey.shoppinglist.domain

class DeleteShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    fun deleteShopItem(id: Int): Boolean {
        return shopItemRepository.deleteShopItem(id)
    }

}