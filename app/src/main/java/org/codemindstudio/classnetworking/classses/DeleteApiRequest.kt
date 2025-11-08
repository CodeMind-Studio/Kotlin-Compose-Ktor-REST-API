package org.codemindstudio.classnetworking.classses

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.delete
import io.ktor.client.request.header
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.codemindstudio.classnetworking.modals.post.AddProductRequest
import org.codemindstudio.classnetworking.modals.post.AddProductResponse
import org.codemindstudio.classnetworking.modals.post.Data

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
fun DeleteDeviceUI() {


    var isLoading by remember { mutableStateOf(false) }
    var responseMsg by remember { mutableStateOf("") }
    var productName by remember { mutableStateOf("") }


    val context = LocalContext.current
    val scope = rememberCoroutineScope()


    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }


    Box(Modifier.fillMaxSize()) {

        Button(modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(18.dp), onClick = {
            isLoading = true
            scope.launch {
                val response = client.delete("https://api.restful-api.dev/objects/ff8081819782e69e019a620f63b10a7c").body<DeleteApiRequestResponse>()

                isLoading = false
                responseMsg =
                    "The Product Deleted from Server"
            }
        }) {
            Text("Delete Object Data from Server")
        }

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else {
            Text(
                text = responseMsg,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.align(Alignment.Center)
            )
            println(responseMsg)
        }
    }

    // Toast.makeText(context, responseMsg, Toast.LENGTH_LONG).show()


}

@Serializable
data class DeleteApiRequestResponse(
    val message : String = ""
)