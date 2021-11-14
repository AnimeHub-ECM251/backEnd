package controllers



import com.google.gson.Gson
import models.Anime
import repositories.IRepo
import repositories.mysql.RepoMysql
import usecases.UcAnime



class CtrlAnime (rep: IRepo) {
    val repo : IRepo
    val ucAnime : UcAnime

    init {
        repo = rep
        ucAnime = UcAnime(rep)
    }

    fun createAnime(body: String): String {
        var map: HashMap<String, String> = HashMap()
        map = Gson().fromJson(body, map.javaClass)
        val a : Anime = Anime.fromHashMap(map)
        ucAnime.createAnime(a)
        return "Anime ${a.title} was created"
    }

    fun updateAnime(idAnime: Int, body: String): String {
        var map: HashMap<String, String> = HashMap()
        map = Gson().fromJson(body, map.javaClass)
        map["id"] = idAnime.toString()
        ucAnime.updateAnime(map)
        return "Anime $idAnime was updated"
    }

    fun deleteAnime(id: Int): String {
        ucAnime.deleteAnime(id)
        return "Anime #${id} was deleted"
    }

    fun getAnimeById(id: String): Anime {
        return ucAnime.getAnime(id.toInt())
    }

    fun getAllAnimes(): List<Anime> {
        return ucAnime.getAllAnimes()
    }

    fun getAllAnimesIds(): List<Int> {
        val animesList = getAllAnimes()
        val animesIds = ArrayList<Int>()
        for (anime in animesList) {
            animesIds.add(anime.id!!)
        }
        return animesIds
    }

    fun getAnimesPage(page: Int?): String {
        val animesIds = getAllAnimesIds()
        val pageSize = 5
        val startIndex = pageSize * (page!! - 1)
        val endIndex = startIndex + pageSize
        val animesPageId = ArrayList<Int>()

        for (id in startIndex until endIndex) {
            if (animesIds.indices.contains(id)) {
                animesPageId.add(animesIds[id])
            } else {
                break
            }
        }
        return animesPageId.toString()
    }

}

fun main(){
    val repo = RepoMysql()
    val ctrl = CtrlAnime(repo)
//    ctrl.createAnime("""{
//        "title": "KonoSuba2",
//        "image": "image",
//        "synopsis": "syn",
//        "episodes": "30",
//        "launchDate": 2021-10-10,
//        "studio": "stud"}""")
//    ctrl.deleteAnime(13)
    println(ctrl.getAllAnimesIds())
}
