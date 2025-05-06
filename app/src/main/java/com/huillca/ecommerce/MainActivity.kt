package com.huillca.ecommerce
import androidx.compose.foundation.layout.Column
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.huillca.ecommerce.model.Producto
import com.huillca.ecommerce.ui.ProductListScreen
import com.huillca.ecommerce.ui.ProductoViewModel
import com.huillca.ecommerce.ui.RegisterProductScreen
import com.huillca.ecommerce.ui.theme.EcommerceTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import android.util.Log
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    private val productoViewModel: ProductoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcommerceTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "register_product"
                ) {
                    composable("register_product") {
                        RegisterProductScreen { nombre, descripcion, precio, cantidad ->
                            val producto = Producto(
                                nombre = nombre,
                                descripcion = descripcion,
                                precio = precio,
                                cantidad = cantidad
                            )
                            productoViewModel.insertProducto(producto)

                            // Navegar a la lista de productos después de registrar
                            navController.navigate("product_list")
                        }
                    }

                    composable("product_list") {
                        ProductListScreen(
                            productosFlow = productoViewModel.productos,
                            onEdit = { producto ->
                                val updatedProducto = producto.copy(nombre = "Nuevo Nombre")
                                productoViewModel.updateProducto(updatedProducto)
                            },
                            onDelete = { producto ->
                                productoViewModel.deleteProducto(producto)
                            }
                        )
                    }
                }
            }
        }
    }
}
