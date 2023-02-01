package com.example.shopping_list.data.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.shopping_list.data.local.entity.ShoppingItem
import com.example.shopping_list.domain.model.ImageResponse
import com.example.shopping_list.domain.repo.ShoppingRepo
import com.example.shopping_list.utiles.Resource

class TestShoppingRepo: ShoppingRepo {
    /**
     * in this repo we will make a punch of private variables to simulate the behaviour of the db.
     * this way testing will be much faster, and we don't test database it self.
     */
    private val shoppingItems = mutableListOf<ShoppingItem>()
    private val observableShoppingItems = MutableLiveData<List<ShoppingItem>>()
    private val observableTotalPrice = MutableLiveData<Double>()

    private var shouldApiReturnsError = false

    private fun refreshObservables() {
        observableShoppingItems.postValue(shoppingItems)
        observableTotalPrice.postValue(getTotalPrice())
    }

    private fun getTotalPrice(): Double {
        var sum = 0.0
        for (shoppingItem in shoppingItems) {
            sum += shoppingItem.totalPrice
        }
        return sum
    }

    override suspend fun insertShoppingItme(item: ShoppingItem) {
        shoppingItems.add(item)
        refreshObservables()
    }

    override suspend fun deleteShoppingItem(item: ShoppingItem) {
        shoppingItems.remove(item)
    }

    override suspend fun getShoppingItems(): LiveData<List<ShoppingItem>> {
        return observableShoppingItems
    }

    override suspend fun shoppingItemsTotalPrice(): LiveData<Double> {
        return observableTotalPrice
    }

    override suspend fun searchForImages(searchQuery: String): Resource<ImageResponse?> {
        return if (shouldApiReturnsError) {
            Resource.success(data = ImageResponse(listOf(), 0, 0))
        } else {
            Resource.error(message = "error", data = null)
        }
    }
}