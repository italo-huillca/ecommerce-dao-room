package com.huillca.ecommerce.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.huillca.ecommerce.db.AppDatabase
import com.huillca.ecommerce.model.Producto
import kotlinx.coroutines.launch

class ProductoViewModel(application: Application) : AndroidViewModel(application) {
    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "ecommerce_db"
    ).build()

    private val productoDao = db.productoDao()

    fun insertProducto(producto: Producto) {
        viewModelScope.launch {
            productoDao.insertProducto(producto)
        }
    }
}