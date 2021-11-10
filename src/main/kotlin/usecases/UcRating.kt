package usecases

import models.Comment
import models.Rating
import models.Watch_List
import repositories.IRepo
import repositories.mysql.RepoMysql

class UcRating(rep: IRepo){
    val repo: IRepo

    init {
        this.repo = rep
    }

    fun create(rating: Rating) {
        this.repo.create("Rating", rating.toHashMap())
    }

    fun update(rating: Rating) {
        this.repo.update("Rating", rating.toHashMap())
    }

    fun delete(id: Int) {
        this.repo.delete("Rating", id)
    }

    fun get(id: String?): Rating {
        val map = this.repo.getById("Rating", id)
        val rating = Rating.fromHashMap(map as HashMap<String, String>)
        return rating
    }

    fun insert(r: Rating): String {
        // checks if the user and anime are already in the watch list
        val ratingList = repo.getAll("Rating","idUser = ${r.idUser} AND idAnime = ${r.idAnime}")
        if (ratingList.isEmpty()) {
            this.repo.create("Rating", r.toHashMap())
            return "Anime was inserted in Rating"
        } else {
            var r_map = r.toHashMap()
            r_map["id"] = ratingList[0]["id"]!!
            this.repo.update("Rating", r_map)
            return "Anime was updated in Rating (${ratingList[0]["rating"]} → ${r.rating})"
        }

    }

    fun getUserRating(userId: Int, animeId: Int): Int {
        val ratingList = repo.getAll("Rating","idUser = $userId AND idAnime = $animeId")
        if (ratingList.isEmpty()) {
            return -1
        } else {
            return ratingList[0]["rating"]!!.toInt()
        }


    }

}

fun main() {
    val repo = RepoMysql()
    val uc = UcRating(repo)
    val r = Rating(-1,1,2, 3)
    println(uc.insert(r))
}