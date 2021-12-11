package com.suhovey.shoppinglist.domain

class GetListShopItemUseCase(private val shopItemRepository: ShopItemRepository) {

    fun getListShopItems(): List<ShopItem> {

        return shopItemRepository.getListShopItem()

    }

}