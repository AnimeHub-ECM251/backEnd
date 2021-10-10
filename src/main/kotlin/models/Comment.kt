package models

data class Comment (
    val id: Int,
    var text: String,
    var likes: Int,
    var deslikes: Int,
    val idUser: Int,
    val idReview: Int
        )