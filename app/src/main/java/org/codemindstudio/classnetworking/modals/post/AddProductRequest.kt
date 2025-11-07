package org.codemindstudio.classnetworking.modals.post


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddProductRequest(
    @SerialName("data")
    val `data`: Data = Data(),
    @SerialName("name")
    val name: String = "" // Apple MacBook Pro 16
)