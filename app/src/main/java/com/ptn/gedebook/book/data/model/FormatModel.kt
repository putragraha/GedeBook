package com.ptn.gedebook.book.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FormatModel(
    @Json(name = "application/x-mobipocket-ebook") val xMobipocketEBook: String? = null,
    @Json(name = "application/epub+zip") val ePubZip: String? = null,
    @Json(name = "application/rdf+xml") val rdfXml: String? = null,
    @Json(name = "text/plain; charset=us-ascii") val textHtmlUtf8: String? = null,
    @Json(name = "text/html") val textHtml: String? = null,
    @Json(name = "application/octet-stream") val zip: String? = null,
    @Json(name = "text/plain") val textPlain: String? = null,
    @Json(name = "image/jpeg") val imageJpeg: String? = null
)
