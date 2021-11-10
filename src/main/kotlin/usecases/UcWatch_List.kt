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
        if (watchList.isEmpty()) {
            this.repo.create("Watch_List", w.toHashMap())
            return "Anime was inserted in Watchlist"
        } else {
            this.repo.delete("Watch_List",watchList[0]["id"]!!.toInt())
            return "Anime was removed in Watchlist"
        }
    }

    fun checkWatchlist(idAnime: Int, idUser: Int): Boolean {
        val watchList = repo.getAll("Watch_List","idUser = ${idUser} AND idAnime = ${idAnime}")
        return watchList.isNotEmpty()

    }

}

fun main() {
    val repo = RepoMysql()
    val uc = UcWatch_List(repo)
    val w = Watch_List(-1,1,3)
    uc.insert(w)

}