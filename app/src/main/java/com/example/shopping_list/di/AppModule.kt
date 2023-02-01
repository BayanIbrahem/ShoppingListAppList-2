package com.example.shopping_list.di

import android.content.Context
import androidx.room.Room
import com.example.shopping_list.data.local.ShoppingDatabase
import com.example.shopping_list.data.local.ShoppingDoa
import com.example.shopping_list.data.remote.PexabayApi
import com.example.shopping_list.data.repo.ShoppingRepo_Impl
import com.example.shopping_list.domain.repo.ShoppingRepo
import com.example.shopping_list.utiles.Constants.DATABASE_NAME
import com.example.shopping_list.utiles.Constants.PEXABAY_BASEURL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun getShoppingDatabase (@ApplicationContext context: Context): ShoppingDatabase {
        return Room.databaseBuilder(
            context,
            ShoppingDatabase::class.java,
            DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun getShoppingDao (db: ShoppingDatabase) = db.dao

    @Provides
    @Singleton
    fun getPexaApiRepo (): PexabayApi {
        return Retrofit.Builder()
            .baseUrl(PEXABAY_BASEURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(PexabayApi::class.java)
    }

    @Provides
    @Singleton
    fun getShoppingRepo (dao: ShoppingDoa, api: PexabayApi): ShoppingRepo {
        return ShoppingRepo_Impl (dao, api)
    }


}