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

    fun checkWatchlist(idAnime: Int, idUser: Int): Boolean{
        return ucWatch_List.checkWatchlist(idAnime, idUser)

    }

    fun getUserWatchList(idUser: Int) : String{
        val list = ucWatch_List.getUserWatchList(idUser)
        return Gson().toJson(list)

    }


}