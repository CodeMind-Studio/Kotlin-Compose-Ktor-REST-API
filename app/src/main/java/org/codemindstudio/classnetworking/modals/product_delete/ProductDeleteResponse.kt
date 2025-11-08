package org.codemindstudio.classnetworking.modals.product_delete


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductDeleteResponse(
    @SerialName("id")
    val id: Int = 0, // 1
    @SerialName("isDeleted")
    val isDeleted: Boolean = false, // true
    @SerialName("title")
    val title: String = "" // Essence Mascara Lash Princess
)