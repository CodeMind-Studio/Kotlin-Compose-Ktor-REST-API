package org.codemindstudio.classnetworking.classses.get

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import org.codemindstudio.classnetworking.modals.get.Product

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


// Products UI

@Composable
fun ProductsUI(modifier: Modifier = Modifier) {
//    var productsDataResponse by remember { mutableStateOf<List<Product>>(emptyList()) }
    var productsDataResponse by remember { mutableStateOf<String>("") }

    var objectClicked = 1
    var objectClicked2 = 4

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }


    LaunchedEffect(Unit) {
//        val response = client.get("https://dummyjson.com/products").bodyAsText()
//        val response = client.get("https://api.restful-api.dev/objects").bodyAsText()
//        val response =
//            client.get("https://api.restful-api.dev/objects?id=$objectClicked&id=$objectClicked2")
//                .bodyAsText()
        val response =
            client.get("https://api.restful-api.dev/objects"){
                parameter(
                    "id",
                    objectClicked,
                )
                parameter(
                    "id",
                    objectClicked2,
                )
            }
                .bodyAsText()
        productsDataResponse = response
    }


    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        Text(productsDataResponse, style = MaterialTheme.typography.labelMedium)

        //        if (productsDataResponse.isEmpty()) {
//            CircularProgressIndicator()
//        } else {
//            LazyColumn {
//                items(productsDataResponse) {
//                    ProductUnitUI(it)
//                }
//            }
//        }
    }

}


@Composable
fun ProductUnitUI(product: Product) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(Color.LightGray.copy(.3f), RoundedCornerShape(6))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {

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
    }
}


@Composable
fun PhotosUI(modifier: Modifier = Modifier) {
    var photos by remember { mutableStateOf<List<PhotosItem>>(emptyList()) }

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    LaunchedEffect(Unit) {
        val response =
            client.get("https://jsonplaceholder.typicode.com/photos").body<List<PhotosItem>>()
        photos = response
    }

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (photos.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(photos) {
                    PhotosItemUI(it)
                }
            }
        }
    }


}


@Composable
fun PhotosItemUI(photosItem: PhotosItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(Color.LightGray.copy(.3f), RoundedCornerShape(20))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = photosItem.title.toString(), style = MaterialTheme.typography.labelSmall)
        Text(text = photosItem.url.toString(), style = MaterialTheme.typography.labelSmall)
        Text(text = photosItem.thumbnailUrl.toString(), style = MaterialTheme.typography.labelSmall)
    }

}

@Serializable
data class PhotosItem(
    val albumId: Int,
    val id: Int,
    val title: String,
    val url: String,
    val thumbnailUrl: String
)

@Composable
fun PostsListUI() {
    var posts by remember { mutableStateOf<List<PostsItem>>(emptyList()) }

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    LaunchedEffect(Unit) {
        val response =
            client.get("https://jsonplaceholder.typicode.com/posts").body<List<PostsItem>>()
        println(response)
        posts = response
    }


    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (posts.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(posts) {
                    PostItemUi(it)
                }
            }
        }
    }

}

@Composable
fun PostItemUi(postsItem: PostsItem) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .background(Color.LightGray.copy(.3f), RoundedCornerShape(20))
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = postsItem.title, style = MaterialTheme.typography.titleMedium)
        Text(text = postsItem.body, style = MaterialTheme.typography.bodySmall)
        Text(text = postsItem.id.toString(), style = MaterialTheme.typography.labelSmall)
        Text(text = postsItem.userId.toString(), style = MaterialTheme.typography.labelSmall)
    }
}


@Serializable
data class PostsItem(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
