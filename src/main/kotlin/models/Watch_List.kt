package models

import com.google.gson.Gson
import models.errors.INTANCE_PROPERTIES_DONT_MATCH

data class Watch_List (
    val id: Int,
    val idUser: Int,
    val idAnime: Int): ITable{

        override fun toHashMap(): HashMap<String, String> {
                return hashMapOf(
                        "id" to id.toString(),
                        "idUser" to idUser.toString(),
                        "idAnime" to idAnime.toString()
                )
        }

        companion object {
            private val properties : List<String> = listOf("idUser", "idAnime")

            fun fromHashMap(map: HashMap<String, String>) : Watch_List{
                val w = map.keys.toList()
                if (w.containsAll(Watch_List.properties)) {
                    return Watch_List(
                        map["id"]?.toInt()?:-1,
                        map["idUser"]!!.toInt(),
                        map["idAnime"]!!.toInt()
                    )
                } else throw INTANCE_PROPERTIES_DONT_MATCH()
            }
        }

    override fun toString(): String {
        val map = this.toHashMap()
        return Gson().toJson(map)
    }

}