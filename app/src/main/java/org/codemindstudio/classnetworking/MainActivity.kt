package org.codemindstudio.classnetworking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.codemindstudio.classnetworking.classses.DeleteDeviceUI
import org.codemindstudio.classnetworking.classses.get.ProductsUI
import org.codemindstudio.classnetworking.classses.patch.PatchDeviceUI
import org.codemindstudio.classnetworking.classses.post.ADDDeviceUI
import org.codemindstudio.classnetworking.classses.put.UpdateDeviceUI
import org.codemindstudio.classnetworking.sample_app.ShowAllProductsUI
import org.codemindstudio.classnetworking.ui.theme.ClassNetworkingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClassNetworkingTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.White
                ) { innerPadding ->
                    Box(Modifier.padding(innerPadding)) {
                        ShowAllProductsUI()
                    }
                }
            }
        }
    }
}

