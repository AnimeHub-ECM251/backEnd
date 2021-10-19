package models





import models.errors.INTANCE_PROPERTIES_DONT_MATCH
import java.time.LocalDate
import kotlin.reflect.full.declaredMemberProperties

data class Anime(
    var title: String,
    var id: Int?,
    var image: String,
    var synopsis: String,
    var episodes: Int,
    var launchDate: LocalDate,
    var studio: String) : ITable {

    override fun toHashMap(): HashMap<String, String> {
        return hashMapOf(
            "title" to title,
            "id" to id.toString(),
            "image" to image,
            "synopsis" to synopsis,
            "episodes" to episodes.toString(),
            "launchDate" to launchDate.toString(),
            "studio" to studio
        )
    }

    companion object {
        val properties : List<String> = listOf("title", "image", "synopsis", "episodes", "launchDate", "studio")

        fun fromHashMap(map: HashMap<String, String>) : Anime{
            val a = map.keys.toList()
            if (a.containsAll(Anime.properties)) {
                val a = Anime(map["title"]!!, map["id"]?.toInt(), map["image"]!!,
                    map["synopsis"]!!, map["episodes"]!!.toInt(), LocalDate.parse(map["launchDate"]!!), map["studio"]!!)
                return a

            } else throw INTANCE_PROPERTIES_DONT_MATCH()

        }
    }

}


fun main(){
    Anime.fromHashMap(hashMapOf("title" to "a", "id" to "2", "image" to "a", "synopsis" to "a", "episodes" to "10",
        "launchDate" to "2021-11-10", "studio" to "a"))
//    val date = LocalDate.parse("2021-11-10")
//    val a = Anime("title", 2, "dasd", "sy", 15, date, "studio")

}