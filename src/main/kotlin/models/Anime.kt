package models





import com.google.gson.Gson
import models.errors.INTANCE_PROPERTIES_DONT_MATCH
import java.time.LocalDate

data class Anime(
    var title: String,
    var id: Int?,
    var image: String,
    var synopsis: String,
    var episodes: Int,
    var launchDate: LocalDate,
    var studio: String,
    var publicRating: Double,
    var websiteRating: Double) : ITable {

    override fun toHashMap(): HashMap<String, String> {
        return hashMapOf(
            "title" to title,
            "id" to id.toString(),
            "image" to image,
            "synopsis" to synopsis,
            "episodes" to episodes.toString(),
            "launchDate" to launchDate.toString(),
            "studio" to studio,
            "publicRating" to publicRating.toString(),
            "websiteRating" to websiteRating.toString(),
        )
    }

    companion object {
        val properties : List<String> = listOf("title", "image", "synopsis", "episodes", "launchDate", "studio")

        fun fromHashMap(map: HashMap<String, String>) : Anime{
            val a = map.keys.toList()
            if (a.containsAll(Anime.properties)) {
                return Anime(
                    map["title"]!!, map["id"]?.toInt(), map["image"]!!,
                    map["synopsis"]!!, map["episodes"]!!.toInt(), LocalDate.parse(map["launchDate"]!!),
                    map["studio"]!!, map["publicRating"]?.toDouble() ?: -1.0, map["websiteRating"]?.toDouble() ?: -1.0
                )
            } else throw INTANCE_PROPERTIES_DONT_MATCH()
        }
    }

    override fun toString(): String {
        val map = this.toHashMap()
        return Gson().toJson(map)
    }

}


