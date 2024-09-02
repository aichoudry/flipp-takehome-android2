package com.flipp.assignment.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.flipp.assignment.R

@Composable
fun FlyerSearchResult(
  name: String?,
  currentPrice: Double?,
  prePrice: String?,
  postPrice: String?,
  imageUrl: String?,
  merchantName: String?,
  merchantLogo: String?,
) {
  Card(
    modifier = Modifier
      .fillMaxWidth()
      .height(150.dp)
  ) {
    Row(
      modifier = Modifier
        .fillMaxHeight()
        .fillMaxWidth(),
      verticalAlignment = Alignment.CenterVertically,
      horizontalArrangement = Arrangement.spacedBy(
        space = 16.dp,
        alignment = Alignment.CenterHorizontally
      )
    ) {
      AsyncImage(
        model = imageUrl,
        placeholder = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = "Image of $name",
        modifier = Modifier
          .size(125.dp)
      )
      Column(
        modifier = Modifier
          .height(125.dp)
          .fillMaxWidth(0.90f),
        verticalArrangement = Arrangement.spacedBy(8.dp),
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          merchantLogo?.let {
            AsyncImage(
              model = merchantLogo,
              placeholder = painterResource(id = R.drawable.ic_launcher_foreground),
              contentDescription = "Image of $merchantName",
              modifier = Modifier
                .width(40.dp)
            )
          }
          merchantName?.let {
            Column {
              Text(merchantName, style = MaterialTheme.typography.titleSmall)
            }
          }
        }
        Row {
          Column {
            name?.let {
              Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
              )
            }
            Row(
              verticalAlignment = Alignment.CenterVertically,
              horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
              currentPrice?.let {
                Text("${prePrice ?: ""}$$currentPrice${postPrice ?: ""}")
              }
            }
          }
        }
      }
    }

  }
}

@Preview
@Composable
fun FlyerSearchResultPreview() {
  FlyerSearchResult(
    imageUrl = "https://f.wishabi.net/page_items/347935456/1724466643/extra_large.jpg",
    currentPrice = 1.99,
    merchantLogo = "https://images.wishabi.net/merchants/2337/1526504338/storefront_logo",
    merchantName = "Your Independent Grocer",
    name = "PC® WHOLE CHICKEN",
    prePrice = "ONLY",
    postPrice = "lb",
  )
}
