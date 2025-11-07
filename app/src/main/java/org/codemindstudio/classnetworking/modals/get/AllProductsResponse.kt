package org.codemindstudio.classnetworking.modals.get


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllProductsResponse(
    @SerialName("limit")
    val limit: Int = 0, // 30
    @SerialName("products")
    val products: List<Product> = listOf(),
    @SerialName("skip")
    val skip: Int = 0, // 0
    @SerialName("total")
    val total: Int = 0 // 194
)