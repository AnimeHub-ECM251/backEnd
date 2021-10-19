package usecases

import models.Anime
import repositories.IRepo

class UcAnime (rep: IRepo) {
    val repo: IRepo

    init {
        this.repo = rep
    }

    fun createAnime(anime: Anime){
        this.repo.create("Anime", anime.toHashMap())
    }

    fun updateAnime(anime: Anime){
        this.repo.update("Anime", anime.toHashMap())
    }

    fun deleteAnime(id: Int) {
        this.repo.delete("Anime", id)
    }

    fun getAnime(id: String?): Anime {
        val map = this.repo.getById("Anime", id)
        val anime = Anime.fromHashMap(map as HashMap<String, String>)
        return anime
    }


}