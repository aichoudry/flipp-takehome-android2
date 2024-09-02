package com.flipp.assignment.ui.parameterprovider

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.flipp.assignment.feature.search.EcomItem
import com.flipp.assignment.feature.search.FlyerItem
import com.flipp.assignment.feature.search.SearchResultUIState
import com.flipp.assignment.ui.parameterprovider.SearchResultPreviewParameterData.ecomItems
import com.flipp.assignment.ui.parameterprovider.SearchResultPreviewParameterData.flyerItems

class SearchResultPreviewParameterProvider : PreviewParameterProvider<SearchResultUIState> {

  override val values: Sequence<SearchResultUIState> =
    sequenceOf(SearchResultUIState.Success(flyerItems = flyerItems, ecomItems = ecomItems))
}

object SearchResultPreviewParameterData {

  val ecomItems: List<EcomItem> = listOf(
    EcomItem(
      currentPrice = 16.88,
      description = null,
      globalId = null,
      globalIdInt = null,
      imageUrl = "https://f.wishabi.net/item-assets/234/d98da371ee99e1b479dd579dfb8b8da6/small.png",
      merchant = "Walmart",
      merchantId = 3678,
      merchantLogo = "https://images.wishabi.net/merchants/234/1507309061/234.jpg",
      name = "Caledon Farms Homestyle Chicken Jerky Value Pack, Made With 95% Chicken Variable",
      originalPrice = 18.88,
      sku = null
    ),
    EcomItem(
      currentPrice = 3.49,
      description = null,
      globalId = null,
      globalIdInt = null,
      imageUrl = "https://f.wishabi.net/item-assets/208/18a66f3a919808a04df43a2c5bdcc056/small.png",
      merchant = "Shoppers Drug Mart",
      merchantId = 3678,
      merchantLogo = "https://images.wishabi.net/merchants/208/1507308780/208.jpg",
      name = "Baby Gourmet Fruity chicken & brown rice 128.0 mL",
      originalPrice = null,
      sku = null
    ),
    EcomItem(
      currentPrice = 14.99,
      description = null,
      globalId = null,
      globalIdInt = null,
      imageUrl = "https://f.wishabi.net/item-assets/228/12b832b7fdcd234bca26de80502f5452/small.png",
      merchant = "London Drugs",
      merchantId = 3678,
      merchantLogo = "https://images.wishabi.net/merchants/228/1507309015/228.jpg",
      name = "Masters Best Friend Chicken Jerky Size 454g",
      originalPrice = null,
      sku = null
    )
  )

  val flyerItems: List<FlyerItem> = listOf(
    FlyerItem(
      cleanImageUrl = "https://f.wishabi.net/page_items/347935456/1724466643/extra_large.jpg",
      currentPrice = 1.99,
      flyerId = 5722,
      flyerItemId = 8588,
      merchantLogo = "https://images.wishabi.net/merchants/2337/1526504338/storefront_logo",
      merchantName = "Your Independent Grocer",
      name = "PC® WHOLE CHICKEN",
      originalPrice = 2.99,
      postPriceText = "lb",
      prePriceText = "ONLY"
    ),
    FlyerItem(
      cleanImageUrl = "https://f.wishabi.net/page_items/347715012/1724292914/extra_large.jpg",
      currentPrice = 5.99,
      flyerId = 5722,
      flyerItemId = 8588,
      merchantLogo = "https://images.wishabi.net/merchants/G1fLFjzsWRX1DA==/RackMultipart20191002-1-1aomrj6.jpg",
      merchantName = "FreshCo",
      name = "Ai Safa Halal Breaded Chicken",
      originalPrice = null,
      postPriceText = null,
      prePriceText = null
    ),
    FlyerItem(
      cleanImageUrl = "https://f.wishabi.net/page_items/348183056/1724716320/extra_large.jpg",
      currentPrice = 2.49,
      flyerId = 5722,
      flyerItemId = 8588,
      merchantLogo = "https://images.wishabi.net/merchants/2018/1526504087/storefront_logo",
      merchantName = "Loblaws",
      name = "CHICKEN THIGHS",
      originalPrice = null,
      postPriceText = "lb",
      prePriceText = null
    )
  )

}
