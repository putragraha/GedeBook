package com.ptn.gedebook.book.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonModel(
    @Json(name = "name") val name: String,
    @Json(name = "birth_year") val birthYear: Int? = null,
    @Json(name = "death_year") val deathYear: Int? = null
)
