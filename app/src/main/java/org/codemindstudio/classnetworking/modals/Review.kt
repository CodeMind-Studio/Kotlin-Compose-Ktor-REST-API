package org.codemindstudio.classnetworking.modals


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Review(
    @SerialName("comment")
    val comment: String = "", // Very dissatisfied!
    @SerialName("date")
    val date: String = "", // 2025-04-30T09:41:02.053Z
    @SerialName("rating")
    val rating: Int = 0, // 1
    @SerialName("reviewerEmail")
    val reviewerEmail: String = "", // cameron.perez@x.dummyjson.com
    @SerialName("reviewerName")
    val reviewerName: String = "" // Cameron Perez
)