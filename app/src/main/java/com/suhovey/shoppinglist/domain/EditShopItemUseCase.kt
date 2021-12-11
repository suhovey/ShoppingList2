package com.suhovey.shoppinglist.domain

class EditShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    fun editShopItem(it: ShopItem): Boolean {
        return shopItemRepository.editShopItem(it)
    }

}