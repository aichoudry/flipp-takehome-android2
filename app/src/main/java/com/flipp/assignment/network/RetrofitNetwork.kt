package com.flipp.assignment.network

import com.flipp.assignment.network.model.SearchResultsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkAPI {

  companion object {
    const val BASE_URL = "https://flipp-search-d4f33159a476.herokuapp.com"
  }

  @GET(value = "search")
  suspend fun getSearchResults(
    @Query("postal_code") postalCode: String,
    @Query("q") q: String,
  ): SearchResultsResponse

}