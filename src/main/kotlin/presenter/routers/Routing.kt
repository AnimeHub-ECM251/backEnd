package presenter.routers

import controllers.CtrlAnime
import controllers.CtrlComment
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import repositories.IRepo






fun Application.configureRouting(rep: IRepo) {
    var controladorAnime = CtrlAnime(rep)
    var controladorComment = CtrlComment(rep)

    // Starting point for a Ktor app:
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/nome/{nome}") {
            call.respondText("Hello, ${call.parameters["nome"]}")
        }

        get("/anime/{id}") {
            call.respondText("${controladorAnime.getAnimeById(call.parameters["id"])}")
        }

        post("/criar-anime") {
            val request = call.receive<String>()
            call.respondText(controladorAnime.createAnime(request))
        }

        post("/atualizar-anime") {
            val request = call.receive<String>()
            call.respondText(controladorAnime.updateAnime(request))
        }

        post("/deletar-anime") {
            val request = call.receive<String>()
            println(request.toInt())
            call.respondText(controladorAnime.deleteAnime(request.toInt()))
        }

        get("/todos-animes") {
            call.respondText(controladorAnime.getAllAnimes().toString())
        }

        get("todos-animes/id"){
            call.respondText(controladorAnime.getAllAnimesIds().toString())
        }

        get("/comentarios/{id}") {
            val id = Integer.valueOf(call.parameters["id"])
            call.respondText(controladorComment.getAllCommentsByReview(id))
        }

//        post("/criar-comment") {
//            val request = call.receive<String>()
//            val ctrl = CtrlComment(rep)
//            call.respondText(ctrl.create(request))
//        }
//        post("/atualizar-comment") {
//            val request = call.receive<String>()
//            val ctrl = CtrlComment(rep)
//            call.respondText(ctrl.update(request))
//        }
    }

}
