package usecases

import models.Anime
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

    fun get(id: Int): Rating {
        val map = this.repo.getById("Rating", id)
        val rating = Rating.fromHashMap(map as HashMap<String, String>)
        return rating
    }

    fun insert(r: Rating): String {
        // checks if the user and anime are already in the watch list
        val ratingList = repo.getAll("Rating","idUser = ${r.idUser} AND idAnime = ${r.idAnime}")
        val result : String

        if (ratingList.isEmpty()) {
            this.repo.create("Rating", r.toHashMap())
            result = "Anime was inserted in Rating"
        } else {
            var r_map = r.toHashMap()
            r_map["id"] = ratingList[0]["id"]!!
            this.repo.update("Rating", r_map)
            result = "Anime was updated in Rating (${ratingList[0]["rating"]} → ${r.rating})"
        }
        updatePublicRating(r.idAnime)
        return result

    }

    fun getUserRating(userId: Int, animeId: Int): Int {
        val ratingList = repo.getAll("Rating","idUser = $userId AND idAnime = $animeId")
        if (ratingList.isEmpty()) {
            return -1
        } else {
            return ratingList[0]["rating"]!!.toInt()
        }
    }

    fun updatePublicRating(idAnime: Int) {
        // Get new rating
        val ratingList = this.repo.getAll("Rating", "idAnime = ${idAnime}")
        var sum : Double = 0.0
        var count : Double = 0.0
        for (map in ratingList) {
            sum += map["rating"]!!.toInt()
            count++
        }
        var avg : Double = sum / count
        avg = Math.round(avg * 2) / 2.0

        // Update anime Rating
        val ucAnime = UcAnime(repo)
        var anime = ucAnime.getAnime(idAnime)
        anime.publicRating = avg
        ucAnime.updateAnime(anime.toHashMap())
    }

}

fun main() {
    val repo = RepoMysql()
    val uc = UcRating(repo)
    val r = Rating(-1,1,2, 3)
    println(uc.insert(r))
}