package com.huillca.ecommerce.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.huillca.ecommerce.model.Producto
import kotlinx.coroutines.flow.Flow

@Composable
fun ProductListScreen(productosFlow: Flow<List<Producto>>) {
    val productos by productosFlow.collectAsState(initial = emptyList())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(productos) { producto ->
            ProductCard(producto)
        }
    }
}

@Composable
fun ProductCard(producto: Producto) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = 4.dp
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Nombre: ${producto.nombre}")
            Text(text = "Descripción: ${producto.descripcion}")
            Text(text = "Precio: ${producto.precio}")
            Text(text = "Cantidad: ${producto.cantidad}")
        }
    }
}