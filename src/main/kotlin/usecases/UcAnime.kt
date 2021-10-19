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


}