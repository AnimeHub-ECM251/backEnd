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

    fun updateAnime(anime: HashMap<String, String>){
        this.repo.update("Anime", anime)
    }

    fun deleteAnime(id: Int) {
        this.repo.delete("Anime", id)
    }

    fun getAnime(id: Int): Anime {
        val map = this.repo.getById("Anime", id)
        return Anime.fromHashMap(map as HashMap<String, String>)
    }

    fun getAllAnimes(): List<Anime> {
        val list = this.repo.getAll("Anime")
        val animeList = ArrayList<Anime>()
        for (map in list) {
            animeList.add(Anime.fromHashMap(map as HashMap<String, String>))
        }
        return animeList
    }
}