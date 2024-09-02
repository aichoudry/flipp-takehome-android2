package com.flipp.assignment.network.model


import com.flipp.assignment.feature.search.FlyerItem
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Item(
  @Json(name = "clean_image_url")
  val cleanImageUrl: String?,
  @Json(name = "current_price")
  val currentPrice: Double?,
  @Json(name = "flyer_id")
  val flyerId: Int,
  @Json(name = "flyer_item_id")
  val flyerItemId: Int,
  @Json(name = "id")
  val id: Int?,
  @Json(name = "merchant_logo")
  val merchantLogo: String?,
  @Json(name = "merchant_name")
  val merchantName: String?,
  @Json(name = "name")
  val name: String?,
  @Json(name = "original_price")
  val originalPrice: Double?,
  @Json(name = "post_price_text")
  val postPriceText: String?,
  @Json(name = "pre_price_text")
  val prePriceText: String?,
) {

  fun toUIFlyerItem(): FlyerItem {
    return FlyerItem(
      currentPrice = currentPrice,
      merchantLogo = merchantLogo?.replace("http://", "https://"),
      name = name ?: "",
      cleanImageUrl = cleanImageUrl,
      originalPrice = originalPrice,
      flyerId = flyerId,
      flyerItemId = flyerItemId,
      merchantName = merchantName,
      postPriceText = postPriceText,
      prePriceText = prePriceText
    )
  }
}