package com.flipp.assignment.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.flipp.assignment.ui.parameterprovider.SearchResultPreviewParameterProvider

@Composable
fun SearchRoute(
  modifier: Modifier = Modifier,
  navController: NavController,
  viewModel: SearchViewModel = hiltViewModel(),
) {
  SearchScreen(
    modifier = modifier,
    navController = navController,
    searchResultUIState = viewModel.searchResultUiState.collectAsStateWithLifecycle().value,
    queryUIState = viewModel.queryState.collectAsStateWithLifecycle().value,
    postalCodeUIState = viewModel.postalCodeState.collectAsStateWithLifecycle().value,
    onQueryChanged = viewModel::onQueryChanged,
    onPostalCodeChanged = viewModel::onPostalCodeChanged,
    onSearchTriggered = viewModel::onSearchTrigger
  )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SearchScreen(
  modifier: Modifier = Modifier,
  navController: NavController = rememberNavController(),
  searchResultUIState: SearchResultUIState,
  queryUIState: TextFieldState,
  postalCodeUIState: TextFieldState,
  onQueryChanged: (String) -> Unit,
  onPostalCodeChanged: (String) -> Unit,
  onSearchTriggered: (String) -> Unit
) {

  var isActive by rememberSaveable { mutableStateOf(false) }

  Column(modifier = Modifier.padding(horizontal = 8.dp)) {
    Row(verticalAlignment = Alignment.CenterVertically) {
      if (!isActive) {
        TextField(modifier = Modifier.width(95.dp),
          maxLines = 1,
          onValueChange = onPostalCodeChanged,
          value = postalCodeUIState.text,
          placeholder = { Text("Postal") },
          isError = postalCodeUIState.error != null,
          label = { postalCodeUIState.error?.let { Text(it) } })
      }
      SearchBar(modifier = Modifier.padding(bottom = 8.dp),
        query = queryUIState.text,
        onQueryChange = onQueryChanged,
        onSearch = { isActive = false; onSearchTriggered(it) },
        active = isActive,
        onActiveChange = { isActive = it },
        placeholder = { Text("Search here") }) {}
    }
    LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
      when (searchResultUIState) {
        is SearchResultUIState.Initial -> item { Text("Use the search bar to find some items") }
        is SearchResultUIState.Loading -> item { CircularProgressIndicator() }
        is SearchResultUIState.Failed -> item { Text("Failed") }

        is SearchResultUIState.Success -> {
          if (searchResultUIState.flyerItems.isEmpty()) {
            item { Text("No flyer items for '${queryUIState.text}'") }
          } else {
            item {
              Text("In-Store", style = MaterialTheme.typography.titleMedium)
            }
          }
          items(searchResultUIState.flyerItems) {
            FlyerSearchResult(
              name = it.name,
              currentPrice = it.currentPrice,
              prePrice = it.prePriceText,
              postPrice = it.postPriceText,
              imageUrl = it.cleanImageUrl,
              merchantName = it.merchantName,
              merchantLogo = it.merchantLogo
            )
          }

          if (searchResultUIState.flyerItems.isNotEmpty() && searchResultUIState.ecomItems.isNotEmpty()) {
            item { HorizontalDivider() }
          }
          if (searchResultUIState.ecomItems.isEmpty()) {
            item { Text("No online items for '${queryUIState.text}'") }
          } else {
            item {
              Text("Online", style = MaterialTheme.typography.titleMedium)
            }
          }
          items(searchResultUIState.ecomItems) {
            EcomSearchResult(
              name = it.name,
              originalPrice = it.originalPrice,
              currentPrice = it.currentPrice,
              imageUrl = it.imageUrl,
              merchantName = it.merchant,
              merchantLogo = it.merchantLogo,
            )
          }
        }
      }
    }
  }

}


@Preview
@Composable
internal fun SearchScreenPopulated(
  @PreviewParameter(SearchResultPreviewParameterProvider::class) searchResultUIState: SearchResultUIState
) {
  SearchScreen(searchResultUIState = searchResultUIState,
    queryUIState = TextFieldState(text = "Chicken", error = null),
    postalCodeUIState = TextFieldState(text = "90210", error = null),
    onQueryChanged = {},
    onPostalCodeChanged = {},
    onSearchTriggered = {})
}

@Preview
@Composable
internal fun SearchScreenLoading() {
  SearchScreen(searchResultUIState = SearchResultUIState.Loading,
    queryUIState = TextFieldState("Chicken"),
    postalCodeUIState = TextFieldState("90210"),
    onQueryChanged = {},
    onPostalCodeChanged = { },
    onSearchTriggered = { })
}
