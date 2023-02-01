package com.example.shopping_list.data.repo

import androidx.lifecycle.LiveData
import androidx.room.Dao
import com.example.shopping_list.data.local.ShoppingDatabase
import com.example.shopping_list.data.local.ShoppingDoa
import com.example.shopping_list.data.local.entity.ShoppingItem
import com.example.shopping_list.data.remote.PexabayApi
import com.example.shopping_list.domain.model.ImageResponse
import com.example.shopping_list.domain.repo.ShoppingRepo
import com.example.shopping_list.utiles.Resource
import retrofit2.Response
import javax.inject.Inject

class ShoppingRepo_Impl @Inject constructor(
    val shoppingDao: ShoppingDoa,
    val pexabayApi: PexabayApi,
)
: ShoppingRepo {

    override suspend fun insertShoppingItme(item: ShoppingItem) {
        shoppingDao.insertItem(item)
    }

    override suspend fun deleteShoppingItem(item: ShoppingItem) {
        shoppingDao.deleteItem(item)
    }

    override suspend fun getShoppingItems(): LiveData<List<ShoppingItem>> {
        return shoppingDao.getItems()
    }

    override suspend fun shoppingItemsTotalPrice(): LiveData<Double> {
        return shoppingDao.totalPrice()
    }

    override suspend fun searchForImages(searchQuery: String): Resource<ImageResponse?> {
        return try {
            val response = pexabayApi.searchForImages(searchString = searchQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(data = it)
                } ?: Resource.error(message = "un known error", data = null)
            } else {
                Resource.success(data = null)
            }
        } catch (e: Exception) {
                Resource.error(message = "couldn't connect to server", data = null)
            }
        }
}