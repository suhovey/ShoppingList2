package com.suhovey.shoppinglist.domain

class EditShopItemUseCase(private val shopItemsRepository: ShopItemsRepository) {

    fun editShopItem(it: ShopItemData): Boolean {
        return shopItemsRepository.editShopItem(it)
    }

}