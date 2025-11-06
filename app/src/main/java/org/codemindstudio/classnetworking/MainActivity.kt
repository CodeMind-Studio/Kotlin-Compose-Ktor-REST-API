package org.codemindstudio.classnetworking

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.material3.Scaffold
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
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.Serializable
import org.codemindstudio.classnetworking.modals.AllProductsResponse
import org.codemindstudio.classnetworking.modals.Product
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
                        ProductsUI()
                    }
                }
            }
        }
    }
}


// Products UI

@Composable
fun ProductsUI(modifier: Modifier = Modifier) {
    var productsDataResponse by remember { mutableStateOf<List<Product>>(emptyList()) }

    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }


    LaunchedEffect(Unit) {
        val response = client.get("https://dummyjson.com/products").body<AllProductsResponse>()
        productsDataResponse = response.products
    }


    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (productsDataResponse.isEmpty()) {
            CircularProgressIndicator()
        } else {
            LazyColumn {
                items(productsDataResponse) {
                    ProductUnitUI(it)
                }
            }
        }
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
