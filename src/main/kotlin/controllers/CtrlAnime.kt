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

    fun updateAnime(body: String): String {
        var map: HashMap<String, String> = HashMap()
        map = Gson().fromJson(body, map.javaClass)
        val a : Anime = Anime.fromHashMap(map)
        val id: Int = this.repo.getId("Anime", "title", a.title)
        a.id = id
        ucAnime.updateAnime(a)
        return "Anime ${a.title} was updated"
    }

    fun deleteAnime(id: Int): String {
        ucAnime.deleteAnime(id)
        return "Anime #${id} was deleted"
    }

    fun getAnimeById(id: String?): Anime {
        return ucAnime.getAnime(id)
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
