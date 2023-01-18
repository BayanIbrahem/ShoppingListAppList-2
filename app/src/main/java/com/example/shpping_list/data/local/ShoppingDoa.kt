package com.example.shpping_list.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.shpping_list.data.local.entity.ShoppingItem

@Dao
interface ShoppingDoa {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingItem)
    
    @Delete
    suspend fun deleteItem(item: ShoppingItem)

    // we don't need `suspend` for get items, because we use LiveData with it and it is achieved by default.
    @Query("SELECT * FROM shopping_items")
    fun getItems(): LiveData<List<ShoppingItem>>

    @Query("SELECT SUM(unit_price * amount) FROM shopping_items")
    fun totalPrice() :LiveData<Double>
}
