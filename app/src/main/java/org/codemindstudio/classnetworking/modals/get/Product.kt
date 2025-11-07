package org.codemindstudio.classnetworking.modals.get


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Product(
    @SerialName("availabilityStatus")
    val availabilityStatus: String? = "", // In Stock
    @SerialName("brand")
    val brand: String? = "", // Gucci
    @SerialName("category")
    val category: String? = "", // fragrances
    @SerialName("description")
    val description: String? = "", // Gucci Bloom by Gucci is a floral and captivating fragrance, with notes of tuberose, jasmine, and Rangoon creeper. It's a modern and romantic scent.
    @SerialName("dimensions")
    val dimensions: Dimensions? = Dimensions(),
    @SerialName("discountPercentage")
    val discountPercentage: Double? = 0.0, // 14.39
    @SerialName("id")
    val id: Int? = 0, // 10
    @SerialName("images")
    val images: List<String>? = listOf(),
    @SerialName("meta")
    val meta: Meta? = Meta(),
    @SerialName("minimumOrderQuantity")
    val minimumOrderQuantity: Int? = 0, // 2
    @SerialName("price")
    val price: Double? = 0.0, // 79.99
    @SerialName("rating")
    val rating: Double? = 0.0, // 2.74
    @SerialName("returnPolicy")
    val returnPolicy: String? = "", // No return policy
    @SerialName("reviews")
    val reviews: List<Review>? = listOf(),
    @SerialName("shippingInformation")
    val shippingInformation: String? = "", // Ships overnight
    @SerialName("sku")
    val sku: String? = "", // FRA-GUC-GUC-010
    @SerialName("stock")
    val stock: Int? = 0, // 91
    @SerialName("tags")
    val tags: List<String>? = listOf(),
    @SerialName("thumbnail")
    val thumbnail: String? = "", // https://cdn.dummyjson.com/product-images/fragrances/gucci-bloom-eau-de/thumbnail.webp
    @SerialName("title")
    val title: String? = "", // Gucci Bloom Eau de
    @SerialName("warrantyInformation")
    val warrantyInformation: String? = "", // 6 months warranty
    @SerialName("weight")
    val weight: Int? = 0 // 7
)