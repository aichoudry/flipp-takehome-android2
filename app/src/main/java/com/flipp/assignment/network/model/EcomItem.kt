package com.flipp.assignment.network.model


import com.flipp.assignment.feature.search.EcomItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class EcomItem(
  @Json(name = "average_rating")
  val averageRating: Any?,
  @Json(name = "current_price")
  val currentPrice: Double,
  @Json(name = "description")
  val description: String,
  @Json(name = "display")
  val display: String,
  @Json(name = "flyer_id")
  val flyerId: Int?,
  @Json(name = "global_id")
  val globalId: String,
  @Json(name = "global_id_int")
  val globalIdInt: Long,
  @Json(name = "id")
  val id: Int,
  @Json(name = "image_url")
  val imageUrl: String,
  @Json(name = "item_id")
  val itemId: String,
  @Json(name = "item_type")
  val itemType: String,
  @Json(name = "merchant")
  val merchant: String,
  @Json(name = "merchant_id")
  val merchantId: Int,
  @Json(name = "merchant_logo")
  val merchantLogo: String,
  @Json(name = "name")
  val name: String,
  @Json(name = "original_price")
  val originalPrice: Double?,
  @Json(name = "return_info")
  val returnInfo: ReturnInfo,
  @Json(name = "score")
  val score: Double,
  @Json(name = "shipping_info")
  val shippingInfo: ShippingInfo,
  @Json(name = "shipping_tag_line")
  val shippingTagLine: String,
  @Json(name = "sku")
  val sku: String,
  @Json(name = "spork_url")
  val sporkUrl: String?,
  @Json(name = "total_reviews")
  val totalReviews: Int
) {
  fun toUIEcomItem(): EcomItem {
    return EcomItem(
      currentPrice = currentPrice,
      description = description,
      globalId = globalId,
      globalIdInt = globalIdInt,
      imageUrl = imageUrl,
      merchant = merchant,
      merchantId = merchantId,
      merchantLogo = merchantLogo,
      name = name,
      originalPrice = originalPrice,
      sku = sku,
    )
  }
}