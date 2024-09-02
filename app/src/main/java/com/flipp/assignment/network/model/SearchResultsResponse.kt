package com.flipp.assignment.network.model


import com.flipp.assignment.feature.search.SearchResults
import com.squareup.moshi.Json

data class SearchResultsResponse(
  @Json(name = "ecom_items") val ecomItems: List<EcomItem>,
  @Json(name = "items") val flyerItems: List<Item>,
) {
  fun toUISearchResults(): SearchResults {
    return SearchResults(flyerItems = flyerItems.map { it.toUIFlyerItem() },
      ecomItems = ecomItems.map {
        it.toUIEcomItem()
      })
  }
}