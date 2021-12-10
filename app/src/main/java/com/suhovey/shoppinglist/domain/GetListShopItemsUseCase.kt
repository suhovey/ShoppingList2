package com.suhovey.shoppinglist.domain

class GetListShopItemsUseCase(private val shopItemsRepository: ShopItemsRepository) {

    fun getListShopItems(): List<ShopItemData> {
        return shopItemsRepository.getListShopItems()
    }

}