package com.example.shpping_list.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_items")
data class ShoppingItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null, /* id must be null not to do update instead of insert.*/

    @ColumnInfo(name = "name")
    var name: String,

    @ColumnInfo(name = "unit_price")
    var unitPrice: Double,

    @ColumnInfo(name = "amount")
    val amount: Int,

    @ColumnInfo(name = "image_url")
    val imageUrl: String,
) {
    val totalPrice: Double
        get() = unitPrice * amount
}
