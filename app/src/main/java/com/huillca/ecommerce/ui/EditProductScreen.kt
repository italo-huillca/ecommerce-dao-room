package com.huillca.ecommerce.ui

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.huillca.ecommerce.model.Producto
import kotlinx.coroutines.flow.Flow

@Composable
fun EditProductScreen(
    producto: Producto,
    onSave: (Producto) -> Unit
) {
    var nombre by remember { mutableStateOf(producto.nombre) }
    var descripcion by remember { mutableStateOf(producto.descripcion) }
    var precio by remember { mutableStateOf(producto.precio.toString()) }
    var cantidad by remember { mutableStateOf(producto.cantidad.toString()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(text = "Editar Producto", style = androidx.compose.material3.MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripción") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = precio,
            onValueChange = { precio = it },
            label = { Text("Precio") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = cantidad,
            onValueChange = { cantidad = it },
            label = { Text("Cantidad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                val precioDouble = precio.toDoubleOrNull()
                val cantidadInt = cantidad.toIntOrNull()

                if (precioDouble == null || cantidadInt == null) {
                    Log.d("EditProductScreen", "Invalid input: Precio or Cantidad is not a valid number")
                    return@Button
                }

                val updatedProducto = producto.copy(
                    nombre = nombre,
                    descripcion = descripcion,
                    precio = precioDouble,
                    cantidad = cantidadInt
                )
                onSave(updatedProducto)
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar Cambios")
        }
    }
}