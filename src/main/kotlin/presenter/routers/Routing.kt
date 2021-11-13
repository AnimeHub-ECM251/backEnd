package presenter.routers

import controllers.*
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.request.*
import io.ktor.response.*
import repositories.IRepo






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
                call.respondText("${controladorAnime.getAnimeById(call.parameters["id"])}", ContentType.Application.Json)
            }catch (e : Exception){
                call.respondText("Ops, algo deu errado.\n\n${e.stackTraceToString()}" , ContentType.Application.Json)
            }
        }

        post("/criar-anime") {
            val request = call.receive<String>()
            call.respondText(controladorAnime.createAnime(request))
        }

        post("/atualizar-anime") {
            val request = call.receive<String>() //TODO corrigir
            call.respondText(controladorAnime.updateAnime(request))
        }

        post("/deletar-anime") {
            val request = call.receive<String>()
            println(request.toInt())
            call.respondText(controladorAnime.deleteAnime(request.toInt()))
        }

        get("/todos-animes") {
            try{
                call.respondText(controladorAnime.getAllAnimes().toString())
            }catch (e : Exception){
                call.respondText("Ops, algo deu errado.\n\n${e.stackTraceToString()}" , ContentType.Application.Json)
            }
        }

        get("todos-animes/id/{page}"){
            try{
                val page : Int? = call.parameters["page"]?.toInt()
                call.respondText(controladorAnime.getAnimesPage(page).toString())
            }catch (e : Exception){
                call.respondText("Ops, algo deu errado.\n\n${e.stackTraceToString()}" , ContentType.Application.Json)
            }
        }

        get("/comentarios/{id}") {
            try{
                val id = Integer.valueOf(call.parameters["id"])
                call.respondText(controladorComment.getAllCommentsByReview(id, ))
            }catch (e : Exception){
                call.respondText("Ops, algo deu errado.\n\n${e.stackTraceToString()}" , ContentType.Application.Json)
            }
        }

        post("/criar-comentario") {
            val request = call.receive<String>()
            call.respondText(controladorComment.create(request))
        }

        post("/cadastrar-usuario") {
            val request = call.receive<String>()
            call.respondText(controladorUser.createUser(request))
        }

        post ("/logar-usuario") {
            val request = call.receive<String>()
            call.respondText(controladorUser.login(request))

        }

        post("/anime-watchlist"){
            val request = call.receive<String>()
            call.respondText(controladorWatch_List.insert(request))
        }

        get("/watchlist/{animeId}/{userId}"){
            try{
                val userId = Integer.valueOf(call.parameters["userId"])
                val animeId = Integer.valueOf(call.parameters["animeId"])
                call.respondText(controladorWatch_List.checkWatchlist(animeId, userId).toString())
            }catch (e : Exception){
                call.respondText("Ops, algo deu errado.\n\n${e.stackTraceToString()}" , ContentType.Application.Json)
            }
        }

        post("/user-rating"){
            val request = call.receive<String>()
            call.respondText(controladorRating.insert(request))
        }

        get ("/user-rating/{animeId}/{userId}"){
            try{
                val userId = Integer.valueOf(call.parameters["userId"])
                val animeId = Integer.valueOf(call.parameters["animeId"])
                call.respondText(controladorRating.getUserRating(animeId = animeId, userId = userId))
            }catch (e : Exception){
                call.respondText("Ops, algo deu errado.\n\n${e.stackTraceToString()}" , ContentType.Application.Json)
            }

        }

    }

}
