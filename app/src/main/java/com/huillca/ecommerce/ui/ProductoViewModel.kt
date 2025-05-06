package com.huillca.ecommerce.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.huillca.ecommerce.db.AppDatabase
import com.huillca.ecommerce.model.Producto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import android.util.Log

class ProductoViewModel(application: Application) : AndroidViewModel(application) {
    private val db: AppDatabase = Room.databaseBuilder(
        application,
        AppDatabase::class.java,
        "ecommerce_db"
    ).build()

    private val productoDao = db.productoDao()

    val productos: Flow<List<Producto>> = productoDao.getAllProductos()

    fun insertProducto(producto: Producto) {
        viewModelScope.launch {
            Log.d("ProductoViewModel", "Inserting producto: $producto")
            productoDao.insertProducto(producto)
        }
    }

    fun updateProducto(producto: Producto) {
        viewModelScope.launch {
            productoDao.updateProducto(producto)
        }
    }

    fun deleteProducto(producto: Producto) {
        viewModelScope.launch {
            productoDao.deleteProducto(producto)
        }
    }
}