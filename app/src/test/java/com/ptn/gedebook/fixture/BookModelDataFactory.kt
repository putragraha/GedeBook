package com.ptn.gedebook.fixture

import com.ptn.gedebook.book.data.model.BookModel
import com.ptn.gedebook.book.data.model.BookResultModel
import com.ptn.gedebook.book.data.model.FormatModel
import com.ptn.gedebook.book.data.model.PersonModel

fun newBookModel(
    withCount: Int = 1,
    withNext: String? = null,
    withPrevious: String? = null,
    withBookResultModels: List<BookResultModel> = listOf(newBookResultModel())
) = BookModel(
    count = withCount,
    next = withNext,
    previous = withPrevious,
    bookResultModels = withBookResultModels
)

fun newBookResultModel(
    withId: Int = -1,
    withTitle: String = "",
    withAuthors: List<PersonModel> = listOf(newPersonModel()),
    withSubjects: List<String> = listOf(""),
    withTranslators: List<PersonModel> = listOf(newPersonModel()),
    withBookshelves: List<String> = listOf(""),
    withLanguages: List<String> = listOf(""),
    withMediaType: String = "",
    withFormats: FormatModel = newFormatModel(),
    withDownloadCount: Int = 0,
    withCopyright: Boolean? = null,
) = BookResultModel(
    id = withId,
    title = withTitle,
    authors = withAuthors,
    subjects = withSubjects,
    translators = withTranslators,
    bookshelves = withBookshelves,
    languages = withLanguages,
    mediaType = withMediaType,
    formats = withFormats,
    downloadCount = withDownloadCount,
    copyright = withCopyright
)

fun newPersonModel(
    withName: String = "",
    withBirthYear: Int? = null,
    withDeathYear: Int? = null
) = PersonModel(
    name = withName,
    birthYear = withBirthYear,
    deathYear = withDeathYear
)

fun newFormatModel(
    withXMobipocketEBook: String? = null,
    withEPubZip: String? = null,
    withRdfXml: String? = null,
    withTextHtmlUtf8: String? = null,
    withTextHtml: String? = null,
    withZip: String? = null,
    withTextPlain: String? = null,
    withImageJpeg: String? = null
) = FormatModel(
    xMobipocketEBook = withXMobipocketEBook,
    ePubZip = withEPubZip,
    rdfXml = withRdfXml,
    textHtmlUtf8 = withTextHtmlUtf8,
    textHtml = withTextHtml,
    zip = withZip,
    textPlain = withTextPlain,
    imageJpeg = withImageJpeg
)
