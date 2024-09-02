package com.flipp.assignment.feature.search

data class SearchResults(
  val flyerItems: List<FlyerItem>,
  val ecomItems: List<EcomItem>
)

data class FlyerItem(
  val cleanImageUrl: String?,
  val currentPrice: Double?,
  val flyerId: Int,
  val flyerItemId: Int,
  val merchantLogo: String?,
  val merchantName: String?,
  val name: String?,
  val originalPrice: Double?,
  val postPriceText: String?,
  val prePriceText: String?,
)

data class EcomItem(
  val currentPrice: Double?,
  val description: String?,
  val globalId: String?,
  val globalIdInt: Long?,
  val imageUrl: String?,
  val merchant: String?,
  val merchantId: Int,
  val merchantLogo: String?,
  val name: String?,
  val originalPrice: Double?,
  val sku: String?,
)