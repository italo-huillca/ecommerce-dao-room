package com.huillca.ecommerce.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.huillca.ecommerce.dao.ProductoDao
import com.huillca.ecommerce.model.Producto

@Database(entities = [Producto::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productoDao(): ProductoDao
}