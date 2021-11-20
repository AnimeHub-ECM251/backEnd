package usecases

import models.Rating
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
        return Rating.fromHashMap(map as HashMap<String, String>)
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
        return if (ratingList.isEmpty()) -1 else ratingList[0]["rating"]!!.toInt()
    }

    fun updatePublicRating(idAnime: Int) {
        // Get new rating
        val ratingList = this.repo.getAll("Rating", "idAnime = $idAnime")
        var sum = 0.0
        var count = 0.0
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

