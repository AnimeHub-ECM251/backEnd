package models

import java.sql.Date

data class Anime(
    var title: String,
    val id: Int,
    var image: String,
    var synopse: String,
    var episodes: Int,
    var launchDate: Date,
    var studio: String)
