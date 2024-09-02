package com.flipp.assignment.feature.search

import com.flipp.assignment.data.SearchRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test


class SearchViewModelTest {

  private lateinit var repository: SearchRepository
  private lateinit var viewmodel: SearchViewModel

  @OptIn(ExperimentalCoroutinesApi::class)
  private val testDispatcher = UnconfinedTestDispatcher()

  @Before
  fun setup() {
    repository = mockk()
    viewmodel = SearchViewModel(repository)
    Dispatchers.setMain(testDispatcher)
  }

  @After
  fun tearDown() {
    Dispatchers.resetMain()
  }

  @Test
  fun onQueryChanged_Valid() {
    val query = "chicken"
    viewmodel.onQueryChanged(query)
    assertEquals(query, viewmodel.queryState.value.text)
    assertEquals(null, viewmodel.queryState.value.error)
  }

  @Test
  fun onPostalChanged_ValidCanada() {
    val postal = "L5M8A2"
    viewmodel.onQueryChanged(postal)
    assertEquals(postal, viewmodel.postalCodeState.value.text)
    assertEquals(null, viewmodel.postalCodeState.value.error)
  }

  @Test
  fun onPostalChanged_ValidUSA() {
    val postal = "90210"
    viewmodel.onPostalCodeChanged(postal)
    assertEquals(postal, viewmodel.postalCodeState.value.text)
    assertEquals(null, viewmodel.postalCodeState.value.error)
  }

  @Test
  fun onPostalChanged_Invalid() {
    val postals = listOf("902100", "a ", "902/")
    for (postal in postals) {
      viewmodel.onQueryChanged(postal)
      assertNull(viewmodel.postalCodeState.value.error)
    }
  }

  @Test
  fun onSearchTrigger_InvalidPostal() {
    val query = "chicken"
    val postalcode = "1"

    viewmodel.onPostalCodeChanged(postalcode)
    viewmodel.onSearchTrigger(query)

    assertNotNull(viewmodel.postalCodeState.value.error)
    assertEquals(SearchResultUIState.Initial, viewmodel.searchResultUiState.value)
  }

  @Test
  fun onSearchTrigger_ValidInputs() {
    val query = "chicken"
    val postal = "L5M8A2"

    val flyerItems = listOf(
      FlyerItem(
        cleanImageUrl = null,
        currentPrice = null,
        flyerId = 1,
        flyerItemId = 7308,
        merchantLogo = null,
        merchantName = null,
        name = null,
        originalPrice = null,
        postPriceText = null,
        prePriceText = null
      )
    )
    val ecomItems = listOf(
      EcomItem(
        currentPrice = null,
        description = null,
        globalId = null,
        globalIdInt = null,
        imageUrl = null,
        merchant = null,
        merchantId = 2,
        merchantLogo = null,
        name = null,
        originalPrice = null,
        sku = null
      )
    )
    coEvery { repository.getSearchResults(query, postal) } returns SearchResults(
      flyerItems,
      ecomItems
    )

    viewmodel.onSearchTrigger(query)

    coVerify { repository.getSearchResults(query, postal) }
    assertTrue(viewmodel.searchResultUiState.value is SearchResultUIState.Success)
    assertEquals(
      1,
      (viewmodel.searchResultUiState.value as SearchResultUIState.Success).flyerItems.first().flyerId
    )
    assertEquals(
      2,
      (viewmodel.searchResultUiState.value as SearchResultUIState.Success).ecomItems.first().merchantId
    )

  }

  @Test
  fun onSearchTrigger_ValidInputsFails() {
    val query = "chicken"
    val postal = "L5M8A2"

    coEvery { repository.getSearchResults(query, postal) } returns null

    viewmodel.onSearchTrigger(query)

    coVerify { repository.getSearchResults(query, postal) }
    assertTrue(viewmodel.searchResultUiState.value is SearchResultUIState.Failed)
  }

}