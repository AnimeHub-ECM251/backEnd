package models

data class Review (
    var publicRating: Double,
    var websiteRating: Double,
    val id: Int,
    var review: String,
    val idAnime: Int,
    var views: Int
)