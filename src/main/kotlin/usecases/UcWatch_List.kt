package usecases

import models.Watch_List
import repositories.IRepo
import repositories.mysql.RepoMysql

class UcWatch_List(rep: IRepo){

    val repo: IRepo

    init {
        this.repo = rep
    }

    fun insert(w: Watch_List): String {
        // checks if the user and anime are already in the watch list
        val watchList = repo.getAll("Watch_List","idUser = ${w.idUser} AND idAnime = ${w.idAnime}")
        return if (watchList.isEmpty()) {
            this.repo.create("Watch_List", w.toHashMap())
            "Anime was inserted in Watchlist"
        } else {
            this.repo.delete("Watch_List",watchList[0]["id"]!!.toInt())
            "Anime was removed in Watchlist"
        }
    }

    fun checkWatchlist(idAnime: Int, idUser: Int): Boolean {
        val watchList = repo.getAll("Watch_List","idUser = ${idUser} AND idAnime = ${idAnime}")
        return watchList.isNotEmpty()

    }

    fun getUserWatchList(idUser: Int): List<Int> {
        val watchList = repo.getAll("Watch_List","idUser = ${idUser}")
        val list = mutableListOf<Int>()
        for (w in watchList) {
            if (w["idAnime"] != null) {
                list.add(w["idAnime"]!!.toInt())
            }
        }
        return list
    }

}

