package com.flipp.assignment.data

import com.flipp.assignment.feature.search.SearchResults
import com.flipp.assignment.network.NetworkAPI
import javax.inject.Inject

class SearchRepository @Inject constructor(
  private val networkAPI: NetworkAPI
) : ISearchRepository {

  override suspend fun getSearchResults(
    query: String,
    postalCode: String
  ): SearchResults? {
    return try {
      networkAPI.getSearchResults(postalCode, query).toUISearchResults()
    } catch (e: Exception) {
      null
    }
  }

}

interface ISearchRepository {
  suspend fun getSearchResults(query: String, postalCode: String): SearchResults?
}