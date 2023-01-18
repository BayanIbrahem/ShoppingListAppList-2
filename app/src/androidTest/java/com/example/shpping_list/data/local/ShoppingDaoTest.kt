package com.example.shpping_list.data.local

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.example.shpping_list.data.local.entity.ShoppingItem
import com.example.shpping_list.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.Double
import java.util.concurrent.Executors

@RunWith(AndroidJUnit4::class)
@SmallTest // this means it is unit test.
//@MediumTest // integrated test.
//@LargeTest // ui or end to end test
/** in test we don't need multi threads. */
class ShoppingDaoTest {
    private lateinit var db: ShoppingDatabase
    private lateinit var dao: ShoppingDoa

    // this attribute is a role for the class and it is used for telling the jvm that we need to execute line by line without synchronizing them.
    @get:Rule
    private val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun init() {
        /** in memory here means that this database will not be saved in the storage but only in memory for testings purposes */
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ShoppingDatabase::class.java,
        ).allowMainThreadQueries()
            .setTransactionExecutor(Executors.newSingleThreadExecutor())
            .build()
        dao = db.dao
    }
    @After
    fun finish() {
        db.close()
    }

    @Test
    fun insertShoppingItem() = runTest { // it is runBlocking for test.
        val item = ShoppingItem(name = "item_name", amount = 5, unitPrice = 2.0, imageUrl = "image_url", id = 1)
        dao.insertItem(item)
        val items = dao.getItems().getOrAwaitValue() // `getOrAwaitValue` function will wait life data until it call observe function and get value from it and return it, it is useful for testing not to use multi threading.
        Log.d("insertShoppingItem", "item id is: ${item.id}, and id from db is: ${items.get(0).id}")
        assertThat(items).contains(item)
    }

    /**
     * this test function test if onConflictReplace in insert works fine
     */
    @Test
    fun insertShoppingItemIdOnConflict() = runTest {
        val item_1 = ShoppingItem(id = 1, name = "item_name_1", amount = 1, unitPrice = 2.0, imageUrl = "image_url")
        val item_2 = ShoppingItem(id = 1, name = "item_name_2", amount = 2, unitPrice = 2.0, imageUrl = "image_url")
        dao.insertItem(item_1)
        dao.insertItem(item_2)
        val items = dao.getItems().getOrAwaitValue ()
        assertThat(items.size == 1).isTrue()
    }
    @Test
    fun deleteShoppingItem() = runTest {
        val item = ShoppingItem(name = "item_name", amount = 5, unitPrice = 2.0, imageUrl = "image_url")
        dao.insertItem(item)
        var items = dao.getItems().getOrAwaitValue ()
        val db_item = items[0]
        dao.deleteItem(db_item)
        items = dao.getItems().getOrAwaitValue ()
        Log.d("deleteShoppingItem", "items list: $items")
        assertThat(items).doesNotContain(db_item)
    }
    @Test
    fun getTotalSum() = runTest {
        val item1 = ShoppingItem(name = "item_name1", amount = 5, unitPrice = 2.1, imageUrl = "image_url")
        val item2 = ShoppingItem(name = "item_name2", amount = 2, unitPrice = 1.4, imageUrl = "image_url")
        val item3 = ShoppingItem(name = "item_name3", amount = 0, unitPrice = 2.0, imageUrl = "image_url")
        dao.insertItem(item1)
        dao.insertItem(item2)
        dao.insertItem(item3)
        val totalPrice = dao.totalPrice().getOrAwaitValue ()
        val totalPrice1 = item1.amount * item1.unitPrice
        val totalPrice2 = item2.amount * item2.unitPrice
        val totalPrice3 = item3.amount * item3.unitPrice
        assertThat(totalPrice).isEqualTo(totalPrice1 + totalPrice2 + totalPrice3)
    }
}
