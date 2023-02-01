package com.example.shopping_list.domain.repo

import androidx.lifecycle.LiveData
import com.example.shopping_list.data.local.entity.ShoppingItem
import com.example.shopping_list.domain.model.ImageResponse
import com.example.shopping_list.utiles.Resource

interface ShoppingRepo {

    // local db:
    suspend fun insertShoppingItme(item: ShoppingItem)
    suspend fun deleteShoppingItem(item: ShoppingItem)
    suspend fun getShoppingItems(): LiveData<List<ShoppingItem>>
    suspend fun shoppingItemsTotalPrice(): LiveData<Double>

    // remote api:
    suspend fun searchForImages(searchQuery: String): Resource<ImageResponse?>
}