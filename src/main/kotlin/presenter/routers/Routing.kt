package presenter.routers

import com.google.gson.JsonSyntaxException
import controllers.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import models.Anime
import models.errors.INTANCE_PROPERTIES_DONT_MATCH
import repositories.IRepo
import java.lang.IndexOutOfBoundsException
import java.sql.SQLException


fun defaultExceptions(e: Exception) : String{
    return when(e::class) {
        SQLException::class -> "Erro de sintaxe SQL: $e"
        NumberFormatException::class -> "Erro de formatação numérica: $e"
        INTANCE_PROPERTIES_DONT_MATCH::class -> "Parametros incorretos para anime, seu JSON possui: ${Anime.properties}?"
        JsonSyntaxException::class -> "Seu JSON está incorreto!"
        else -> "Ops, algo deu errado: $e"
    }
}


fun Application.configureRouting(rep: IRepo) {
    var controladorAnime = CtrlAnime(rep)
    var controladorComment = CtrlComment(rep)
    var controladorUser = CtrlUser(rep)
    var controladorWatch_List = CtrlWatch_List(rep)
    var controladorRating = CtrlRating(rep)


    // Starting point for a Ktor app:
    routing {

        get("/") {
            call.respondText("Bem vindo a API Anime Hub! Onde você tem acesso as rotas necessárias para o Front End do Anime Hub! Obrigado pela preferência xD")
        }


        get("/anime/{id}") {
            try{
                call.respondText("${controladorAnime.getAnimeById(call.parameters["id"]!!)}", ContentType.Application.Json)
            } catch (e: IndexOutOfBoundsException){
                call.respondText("Este Anime não existe!", status = HttpStatusCode.BadRequest)
            }
            catch (e: Exception){
                call.respondText(defaultExceptions(e), status = HttpStatusCode.BadRequest)
            }
        }

        post("/criar-anime") {
            try {
                val request = call.receive<String>()
                call.respondText(controladorAnime.createAnime(request))
            } catch (e: Exception){
                call.respondText(defaultExceptions(e), status = HttpStatusCode.BadRequest)
            }
        }

        post("/atualizar-anime/{idAnime}") {
            try {
                val idAnime: Int = call.parameters["idAnime"]!!.toInt()
                val request = call.receive<String>()
                call.respondText(controladorAnime.updateAnime(idAnime, request))
            } catch (e: Exception){
                call.respondText(defaultExceptions(e), status = HttpStatusCode.BadRequest)
            }

        }

        post("/deletar-anime") {
            try{
                val request = call.receive<String>()
                println(request.toInt())
                call.respondText(controladorAnime.deleteAnime(request.toInt()))
            } catch (e: Exception){
                call.respondText(defaultExceptions(e), status = HttpStatusCode.BadRequest)
            }
        }

        get("/todos-animes") {
            try{
                call.respondText(controladorAnime.getAllAnimes().toString())
            } catch (e: Exception){
                call.respondText(defaultExceptions(e), status = HttpStatusCode.BadRequest)
            }
        }

        get("todos-animes/id/{page}"){
            try{
                val page : Int? = call.parameters["page"]?.toInt()
                call.respondText(controladorAnime.getAnimesPage(page).toString())
            } catch (e: Exception){
                call.respondText(defaultExceptions(e), status = HttpStatusCode.BadRequest)
            }
        }

        get("/comentarios/{id}") {
            try{
                val id = Integer.valueOf(call.parameters["id"])
                call.respondText(controladorComment.getAllCommentsByReview(id))
            } catch (e: Exception){
                call.respondText(defaultExceptions(e), status = HttpStatusCode.BadRequest)
            }
        }

        post("/criar-comentario") {
            try {
                val request = call.receive<String>()
                call.respondText(controladorComment.createComment(request))
            } catch (e: Exception){
                call.respondText(defaultExceptions(e), status = HttpStatusCode.BadRequest)
            }
        }

        post("/cadastrar-usuario") {
            try {
                val request = call.receive<String>()
                call.respondText(controladorUser.createUser(request))
            } catch (e: Exception){
                call.respondText(defaultExceptions(e), status = HttpStatusCode.BadRequest)
            }
        }

        post ("/logar-usuario") {
            try {
                val request = call.receive<String>()
                call.respondText(controladorUser.login(request))
            } catch (e: Exception){
                call.respondText(defaultExceptions(e), status = HttpStatusCode.BadRequest)
            }

        }

        post("/anime-watchlist"){
            try {
                val request = call.receive<String>()
                call.respondText(controladorWatch_List.insert(request))
            } catch (e: Exception){
                call.respondText(defaultExceptions(e), status = HttpStatusCode.BadRequest)
            }
        }

        get("/watchlist/{animeId}/{userId}"){
            try{
                val userId = Integer.valueOf(call.parameters["userId"])
                val animeId = Integer.valueOf(call.parameters["animeId"])
                call.respondText(controladorWatch_List.checkWatchlist(animeId, userId).toString())
            } catch (e: Exception){
                call.respondText(defaultExceptions(e), status = HttpStatusCode.BadRequest)
            }
        }

        get("/watchlist/user/{userId}"){
            try{
                val userId = Integer.valueOf(call.parameters["userId"])
                call.respondText(controladorWatch_List.getUserWatchList(userId))
            } catch (e: Exception){
                call.respondText(defaultExceptions(e), status = HttpStatusCode.BadRequest)
            }
        }

        post("/user-rating"){
            try {
                val request = call.receive<String>()
                call.respondText(controladorRating.insert(request))
            } catch (e: Exception){
                call.respondText(defaultExceptions(e), status = HttpStatusCode.BadRequest)
            }
        }

        get ("/user-rating/{animeId}/{userId}"){
            try{
                val userId = Integer.valueOf(call.parameters["userId"])
                val animeId = Integer.valueOf(call.parameters["animeId"])
                call.respondText(controladorRating.getUserRating(animeId = animeId, userId = userId))
            }catch (e: Exception){
                call.respondText(defaultExceptions(e), status = HttpStatusCode.BadRequest)
            }
        }

        get("/get-user/{id}"){
            try{
               val userId = Integer.valueOf(call.parameters["id"])
                call.respondText(controladorUser.getUserById(userId).toString())
            }catch (e: Exception){
                call.respondText(defaultExceptions(e), status = HttpStatusCode.BadRequest)
            }
        }
    }
}
