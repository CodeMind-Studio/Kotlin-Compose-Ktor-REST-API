package org.codemindstudio.classnetworking.modals.post


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("CPU model")
    val cPUModel: String = "", // Intel Core i9
    @SerialName("Hard disk size")
    val hardDiskSize: String = "", // 1 TB
    @SerialName("price")
    val price: Double = 0.0, // 1849.99
    @SerialName("year")
    val year: Int = 0 // 2019
)