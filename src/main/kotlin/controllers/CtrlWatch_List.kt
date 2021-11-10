package controllers

import com.google.gson.Gson
import models.Anime
import models.Watch_List
import repositories.IRepo
import usecases.UcAnime
import usecases.UcWatch_List

class CtrlWatch_List (rep: IRepo) {
    val repo : IRepo
    val ucWatch_List : UcWatch_List

    init {
        repo = rep
        ucWatch_List = UcWatch_List(rep)
    }

    fun insert(body: String): String {
        var map: HashMap<String, String> = HashMap()
        map = Gson().fromJson(body, map.javaClass)
        val w : Watch_List = Watch_List.fromHashMap(map)
        return ucWatch_List.insert(w)

    }
//
//    fun updateAnime(body: String): String {
//        var map: HashMap<String, String> = HashMap()
//        map = Gson().fromJson(body, map.javaClass)
//        val a : Anime = Anime.fromHashMap(map)
//        val id: Int = this.repo.getId("Anime", "title", a.title)
//        a.id = id
//        ucAnime.updateAnime(a)
//        return "Anime ${a.title} was updated"
//    }
//
//    fun deleteAnime(id: Int): String {
//        ucAnime.deleteAnime(id)
//        return "Anime #${id} was deleted"
//    }
//
//    fun getAnimeById(id: String?): Anime {
//        return ucAnime.getAnime(id)
//    }
//
//    fun getAllAnimes(): List<Anime> {
//        return ucAnime.getAllAnimes()
//    }
//
//    fun getAllAnimesIds(): List<Int> {
//        val animesList = getAllAnimes()
//        val animesIds = ArrayList<Int>()
//        for (anime in animesList) {
//            animesIds.add(anime.id!!)
//        }
//        return animesIds
//    }
//
//    fun getAnimesPage(page: Int?): String {
//        val animesIds = getAllAnimesIds()
//        val pageSize = 5
//        val startIndex = pageSize * (page!! - 1)
//        val endIndex = startIndex + pageSize
//        val animesPageId = ArrayList<Int>()
//
//        for (id in startIndex until endIndex) {
//            if (animesIds.indices.contains(id)) {
//                animesPageId.add(animesIds[id])
//            } else {
//                break
//            }
//        }
//        return animesPageId.toString()
//    }

}