package models

data class User(
    var login: String,
    var password: String,
    var email: String,
    var profilePicture: String,
    var role: Int,
    val id: Int
)