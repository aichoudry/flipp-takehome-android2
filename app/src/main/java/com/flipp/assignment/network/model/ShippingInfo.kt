package com.flipp.assignment.network.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ShippingInfo(
    @Json(name = "details_url")
    val detailsUrl: String,
    @Json(name = "tag_line")
    val tagLine: String
)