package org.codemindstudio.classnetworking.sample_app

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.codemindstudio.classnetworking.modals.get.AllProductsResponse
import org.codemindstudio.classnetworking.modals.get.Product
import org.codemindstudio.classnetworking.modals.product_delete.ProductDeleteResponse

/*
 * Copyright (c) $today.year Codemind Studio.
 *
 * All rights reserved.
 *
 * This software and associated documentation files (the "Software") are
 * the confidential and proprietary information of Codemind Studio.
 *
 * Unauthorized copying, modification, distribution, reverse engineering,
 * sublicensing, or any form of commercial or non-commercial use, in whole
 * or in part, is strictly prohibited without a valid written license
 * agreement from Codemind Studio.
 *
 * This Software is protected under applicable intellectual property and
 * copyright laws. All copies must retain this notice and all other
 * proprietary markings of Codemind Studio.
 *
 * Disclaimer:
 * This Software is provided "AS IS," without warranty of any kind,
 * express or implied, including but not limited to warranties of
 * merchantability, fitness for a particular purpose, or non-infringement.
 * In no event shall Codemind Studio be liable for any claim, damages,
 * or other liability, whether in an action of contract, tort, or otherwise,
 * arising from, out of, or in connection with the Software or the use
 * or other dealings in the Software.
 *
 * Private Product – Not for public release.
 */




@Composable
fun ShowAllProductsUI(modifier: Modifier = Modifier) {
    val scope = rememberCoroutineScope()
    var productsDataResponse by remember { mutableStateOf<List<Product>>(emptyList()) }

    var selectedProductId by remember { mutableStateOf<Int?>(null) }


    var selectedProductData by remember { mutableStateOf<Product?>(null) }

    var isLoading by remember { mutableStateOf(false) }

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }
            )
        }
    }


    LaunchedEffect(selectedProductId) {
        if (selectedProductId == null) return@LaunchedEffect
        isLoading = true
        delay(200)
        val response = client.get("https://dummyjson.com/products/$selectedProductId")
            .body<Product>()
        selectedProductData = response
        isLoading = false

    }






    LaunchedEffect(Unit) {
        val response = client.get("https://dummyjson.com/products").body<AllProductsResponse>()
        productsDataResponse = response.products
    }


    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        if (productsDataResponse.isEmpty() || isLoading) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(productsDataResponse) {
                    ProductUnitUI(it, onEditClick = {

                    }, onCardClick = {
                        selectedProductId = it.id
                    }, onDeleteClick = {
                        isLoading = true
                        scope.launch {
                            val response = client.delete("https://dummyjson.com/products/${it.id}")
                                .body<ProductDeleteResponse>()

                            if (response.isDeleted) {
                                productsDataResponse = productsDataResponse.filter { product ->
                                    product.id != it.id
                                }
                            }
                            isLoading = false
                        }
                    })
                }
            }
        }

        if (selectedProductId != null) {
            ShowPopUpProduct(Modifier, selectedProductData ?: Product()) {
                selectedProductId = null
            }
        }


        IconButton(onClick = {},modifier = Modifier.align(Alignment.BottomEnd).padding(12.dp)) {
            Icon(
                Icons.Default.Edit,
                contentDescription = ""
            )
        }

    }

}


@Composable
fun ShowPopUpProduct(modifier: Modifier = Modifier, product: Product, onCloseClick: () -> Unit) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Gray.copy(alpha = .15f))
            .padding(40.dp)
            .background(Color.LightGray, RoundedCornerShape(6))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    )
    {

        AsyncImage(
            model = product.thumbnail,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(text = product.title ?: "Unknown Title", style = MaterialTheme.typography.titleMedium)
        Text(
            text = product.description ?: "No Description",
            style = MaterialTheme.typography.bodySmall
        )
        Text(text = "$" + product.price.toString(), style = MaterialTheme.typography.headlineSmall)

        Row {
            Text(text = "Category: ", style = MaterialTheme.typography.titleMedium)
            Text(text = product.category ?: "Unknown Category", style = MaterialTheme.typography.bodySmall)

        }


        Row(
            Modifier
                .fillMaxWidth()
                .padding(12.dp), horizontalArrangement = Arrangement.End
        ) {

            IconButton(onClick = onCloseClick) {
                Icon(
                    Icons.Default.Close,
                    contentDescription = ""
                )
            }
            Spacer(Modifier.width(12.dp))


        }
    }
}


@Composable
fun ProductUnitUI(
    product: Product,
    onDeleteClick: () -> Unit = {},
    onEditClick: () -> Unit = {},
    onCardClick: () -> Unit = {}
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(Color.LightGray.copy(.3f), RoundedCornerShape(6))
            .padding(12.dp)
            .clickable {
                onCardClick()
            },
        verticalArrangement = Arrangement.spacedBy(4.dp)
    )
    {

        AsyncImage(
            model = product.thumbnail,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        )

        Spacer(modifier = Modifier.height(12.dp))
        Text(text = product.title ?: "Unknown Title", style = MaterialTheme.typography.titleMedium)
        Text(
            text = product.description ?: "No Description",
            style = MaterialTheme.typography.bodySmall
        )
        Text(text = "$" + product.price.toString(), style = MaterialTheme.typography.headlineSmall)

        Row(
            Modifier
                .fillMaxWidth()
                .padding(12.dp), horizontalArrangement = Arrangement.End
        ) {

            IconButton(onClick = onEditClick) {
                Icon(
                    Icons.Default.Edit,
                    contentDescription = ""
                )
            }
            Spacer(Modifier.width(12.dp))

            IconButton(onClick = onDeleteClick) {
                Icon(
                    Icons.Default.Delete,
                    contentDescription = "",
                    tint = Color.Red
                )
            }

        }
    }
}
