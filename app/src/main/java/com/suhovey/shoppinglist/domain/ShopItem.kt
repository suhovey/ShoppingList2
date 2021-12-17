package com.suhovey.shoppinglist.domain

// Определяем структуру элемента содержащего информацию о необходимом товаре, который нужно купить

data class ShopItem(
    val name: String,
    val count: Double,
    val enabled: Boolean,
    var id: Int = UNDEFINED_ID
) {

    companion object {

        const val UNDEFINED_ID = -1

    }

}
