package com.huillca.ecommerce.dao

import androidx.room.*
import com.huillca.ecommerce.model.Producto
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducto(producto: Producto)

    @Update
    suspend fun updateProducto(producto: Producto)

    @Delete
    suspend fun deleteProducto(producto: Producto)

    @Query("SELECT * FROM productos WHERE id = :id")
    suspend fun getProductoById(id: Int): Producto?

    @Query("SELECT * FROM productos ORDER BY nombre ASC")
    fun getAllProductos(): Flow<List<Producto>>
}