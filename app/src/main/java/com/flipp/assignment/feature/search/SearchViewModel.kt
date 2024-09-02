package com.flipp.assignment.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flipp.assignment.data.SearchRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
  private val searchRepository: SearchRepository
) : ViewModel() {

  // The current text in the SearchBar
  private val _queryState = MutableStateFlow(TextFieldState(text = ""))
  val queryState = _queryState.asStateFlow()

  // The current text in the TextField for PostalCode
  private val _postalCodeState = MutableStateFlow(TextFieldState(text = "L5M8A2"))
  val postalCodeState = _postalCodeState.asStateFlow()

  // The state of the SearchResults on the screen
  private val _searchResultsUIState =
    MutableStateFlow<SearchResultUIState>(SearchResultUIState.Initial)
  val searchResultUiState = _searchResultsUIState.asStateFlow()

  /**
   * Updates the queryState with the string q
   *
   * @param q the search query string
   */
  fun onQueryChanged(q: String) {
    _queryState.value = _queryState.value.copy(text = q, error = null)
  }

  /**
   * Updates the postalCodeState iff it satisfies the postal code restrictions
   *  - length <= 6
   *  - only contains alphanumeric characters
   *
   * @param p the new postal code
   */
  fun onPostalCodeChanged(p: String) {
    // Input is <= 6 alphanumeric characters
    if (p.length > 6 || p.any { !it.isLetterOrDigit() }) {
      return
    }
    _postalCodeState.value = _postalCodeState.value.copy(text = p, error = null)
  }

  /**
   * Performs a search given the query q and the postalCodeState.
   * If the query is empty or is an invalid postal code then an error is displayed
   * Updates the searchResultsUIState depending on the result of the API call
   *
   * @param q the search query to perform a search on
   */
  fun onSearchTrigger(q: String) {
    if (q.isEmpty()) {
      _queryState.value = _queryState.value.copy(text = q, error = "Query must not be empty")
      return
    }
    if (!isValidPostalCode(_postalCodeState.value.text)) {
      _postalCodeState.value = _postalCodeState.value.copy(error = "Invalid")
      return
    }
    _searchResultsUIState.value = SearchResultUIState.Loading
    viewModelScope.launch {
      val result = searchRepository.getSearchResults(q, _postalCodeState.value.text)
      _searchResultsUIState.value = when (result) {
        null -> SearchResultUIState.Failed
        else -> SearchResultUIState.Success(
          flyerItems = result.flyerItems, ecomItems = result.ecomItems
        )
      }
    }
  }

  /**
   * Returns true iff p is a valid CAD or USA postal code
   * @param p the postal code being tested
   * @return true iff p is a valid CAD or USA postal code
   */
  private fun isValidPostalCode(p: String): Boolean {
    return Regex("(^\\d{5}(-\\d{4})?$)|(^[A-Za-z]\\d[A-Za-z] ?\\d[A-Za-z]\\d$)").matches(p)
  }
}

/*
  The four states of the SearchResults on the screen
    Initial: No search performed yet
    Loading: A query has been made and is waiting for a result from the server
    Failed: There was an error making the request
    Success: The request was successful
      - flyerItems: A list of the flyer items returned
      - ecomItems: A list of the ecom items returned
 */
sealed interface SearchResultUIState {
  data object Initial : SearchResultUIState
  data object Loading : SearchResultUIState
  data object Failed : SearchResultUIState
  data class Success(val flyerItems: List<FlyerItem>, val ecomItems: List<EcomItem>) :
    SearchResultUIState
}

/*

 */
data class TextFieldState(
  var text: String,
  var error: String? = null,
)
