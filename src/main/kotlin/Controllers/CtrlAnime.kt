package Controllers



import com.google.gson.Gson
import com.google.gson.JsonObject
import io.netty.handler.codec.json.JsonObjectDecoder
import models.Anime
import repositories.IRepo
import repositories.mysql.RepoMysql
import usecases.UcAnime



class CtrlAnime (rep: IRepo) {
    val repo : IRepo
    val ucAnime : UcAnime

    init {
        repo = rep
        ucAnime = UcAnime(rep)
    }

    fun createAnime(body: String) {
        val gson = Gson()
        var map: HashMap<String, String> = HashMap()
        map = Gson().fromJson(body, map.javaClass)
        val a : Anime = Anime.fromHashMap(map)
        ucAnime.createAnime(a)

    }
}

fun main(){
    val repo = RepoMysql()
    val ctrl = CtrlAnime(repo)
    ctrl.createAnime("""{        
        "title": "KonoSuba2",
        "image": "image",
        "synopsis": "syn",
        "episodes": "30",
        "launchDate": 2021-10-10,
        "studio": "stud"}""")
}