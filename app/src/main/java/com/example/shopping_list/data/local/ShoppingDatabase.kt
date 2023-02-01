package com.example.shopping_list.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.shopping_list.data.local.entity.ShoppingItem

@Database(
    entities = [ShoppingItem::class],
    version = 2
)
abstract class ShoppingDatabase: RoomDatabase() {
    abstract val dao: ShoppingDoa

    companion object{
        @Volatile
        private var instance: ShoppingDatabase? = null

        fun getInstance(context: Context): ShoppingDatabase {
            synchronized(this) {
                return instance ?: buildDB(context).also { instance = it }
            }
        }
        private fun buildDB(context: Context, name: String = "shopping_db"): ShoppingDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                ShoppingDatabase::class.java,
                name,
            ).build()
        }
    }
}