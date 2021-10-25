package models

import models.errors.INTANCE_PROPERTIES_DONT_MATCH


data class Comment(
    val id: Int?,
    var text: String,
    var likes: Int,
    var deslikes: Int,
    val idUser: Int,
    val idReview: Int
        ): ITable {
    override fun toHashMap(): HashMap<String, String> {
        return hashMapOf(
            "id" to id.toString(),
            "text" to text,
            "likes" to likes.toString(),
            "deslikes" to deslikes.toString(),
            "idUser" to idUser.toString(),
            "idReview" to idReview.toString()
        )
    }

    companion object {
        private val properties : List<String> = listOf("text", "idUser", "idReview")

        fun fromHashMap(map: HashMap<String, String>) : Comment{
            val mapKeys = map.keys.toList()
            if (mapKeys.containsAll(Comment.properties)) {
                val comment = Comment(map["id"]?.toInt(), map["text"]!!, (map["likes"]?.toInt() ?: 0),
                    (map["deslikes"]?.toInt() ?: 0), map["idUser"]!!.toInt(), map["idReview"]!!.toInt())
                return comment

            } else throw INTANCE_PROPERTIES_DONT_MATCH()

        }
    }
}