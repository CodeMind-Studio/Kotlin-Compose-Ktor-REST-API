package org.codemindstudio.classnetworking.modals


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Meta(
    @SerialName("barcode")
    val barcode: String = "", // 3170832177880
    @SerialName("createdAt")
    val createdAt: String = "", // 2025-04-30T09:41:02.053Z
    @SerialName("qrCode")
    val qrCode: String = "", // https://cdn.dummyjson.com/public/qr-code.png
    @SerialName("updatedAt")
    val updatedAt: String = "" // 2025-04-30T09:41:02.053Z
)