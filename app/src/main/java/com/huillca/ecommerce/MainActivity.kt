package com.huillca.ecommerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.huillca.ecommerce.model.Producto
import com.huillca.ecommerce.ui.ProductoViewModel
import com.huillca.ecommerce.ui.RegisterProductScreen
import com.huillca.ecommerce.ui.theme.EcommerceTheme

class MainActivity : ComponentActivity() {
    private val productoViewModel: ProductoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EcommerceTheme {
                RegisterProductScreen { nombre, descripcion, precio, cantidad ->
                    val producto = Producto(
                        nombre = nombre,
                        descripcion = descripcion,
                        precio = precio,
                        cantidad = cantidad
                    )
                    productoViewModel.insertProducto(producto)
                }
            }
        }
    }
}
