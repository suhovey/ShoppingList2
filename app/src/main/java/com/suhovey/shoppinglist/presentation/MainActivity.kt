package com.suhovey.shoppinglist.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.suhovey.shoppinglist.R

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainShopItemViewModel
    private lateinit var shopItemAdapter: MainShopItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Создаем и настраиваем RecyclerView
        setupRecyclerView()

        // Получаем экземпляр ViewModel
        viewModel =
            ViewModelProvider(this)[MainShopItemViewModel::class.java] // or can use .get(MainShopItemViewModel::class.java)

        // Подписываемся на изменение списка
        viewModel.listShopItem.observe(this) {
            shopItemAdapter.listShopItem = it
        }
    }

    private fun setupRecyclerView() {

        // Ищем RecyclerView по ID
        val rvListShopItem = findViewById<RecyclerView>(R.id.rv_list_shop_item)

        // Создаем экземпляр адаптора
        shopItemAdapter = MainShopItemAdapter()

        // Настраиваем RecyclerView
        with(rvListShopItem) {

            // Устанавливаем адаптер
            adapter = shopItemAdapter

            // Устанавливаем максимальное кол-во элементов в кэше для ShopItem со статусом enabled
            recycledViewPool.setMaxRecycledViews(
                MainShopItemAdapter.TYPE_SHOP_ITEM_IS_ENABLED,
                MainShopItemAdapter.MAX_SIZE_POOL
            )

            // Устанавливаем максимальное кол-во элементов в кэше для ShopItem со статусом disabled
            recycledViewPool.setMaxRecycledViews(
                MainShopItemAdapter.TYPE_SHOP_ITEM_IS_DISABLED,
                MainShopItemAdapter.MAX_SIZE_POOL
            )
        }

        // Устанавливаем слушатель продолжительного клика
        setLongClickLestener()

        // Устанавливаем слушатель обычного клика
        setClickListener()

        // Устанавливаем слушатель свапа
        setTouchListener(rvListShopItem)
    }

    private fun setTouchListener(rvListShopItem: RecyclerView) {

        // Обьявляем callback функцию для обработки события onSwiped
        val callbackItemTouch = object : ItemTouchHelper.SimpleCallback(
            0, // перемещения игнорируем
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT // будем обрабатывать как свайп влево, так и в право
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // получаем ID элемента по которому прошло событие
                val id = shopItemAdapter.listShopItem[viewHolder.adapterPosition].id

                // удаляем элемент из массива
                viewModel.deleteShopItem(id)
            }

        }

        // создаем обьект с указание коллбэк функции
        val itemTouchHelper = ItemTouchHelper(callbackItemTouch)

        // прикрепляемся к списку
        itemTouchHelper.attachToRecyclerView(rvListShopItem)
    }

    private fun setClickListener() {
        shopItemAdapter.onShopItemClickListener = {
            Log.d("ShopItemList", it.toString())
        }
    }

    private fun setLongClickLestener() {
        shopItemAdapter.onShopItemLongClickListener = {
            viewModel.changeEnableShopItem(it)
        }
    }
}