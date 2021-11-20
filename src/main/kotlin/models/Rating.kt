package models

import com.google.gson.Gson
import models.errors.INTANCE_PROPERTIES_DONT_MATCH

data class Rating (
    val id: Int,
    val idUser: Int,
    val idAnime: Int,
    val rating: Int
        ) : ITable{

        override fun toHashMap(): HashMap<String, String> {
            return hashMapOf(
                "id" to id.toString(),
                "idUser" to idUser.toString(),
                "idAnime" to idAnime.toString(),
                "rating" to rating.toString()
            )
        }

        companion object {
            private val properties : List<String> = listOf("idUser", "idAnime", "rating")

            fun fromHashMap(map: HashMap<String, String>) : Rating {
                val r = map.keys.toList()
                if (r.containsAll(Rating.properties)) {
                    return Rating(
                        map["id"]?.toInt()?:-1, map["idUser"]!!.toInt(),
                        map["idAnime"]!!.toInt(), map["rating"]!!.toInt()
                    )
                } else throw INTANCE_PROPERTIES_DONT_MATCH()
            }
        }

    override fun toString(): String {
        val map = this.toHashMap()
        return Gson().toJson(map)
    }

    }