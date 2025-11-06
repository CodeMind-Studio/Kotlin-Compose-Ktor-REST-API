package org.codemindstudio.classnetworking.modals


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Dimensions(
    @SerialName("depth")
    val depth: Double = 0.0, // 11.2
    @SerialName("height")
    val height: Double = 0.0, // 21.68
    @SerialName("width")
    val width: Double = 0.0 // 20.92
)