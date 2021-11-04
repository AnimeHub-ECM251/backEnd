package presenter.routers

import controllers.CtrlAnime
import controllers.CtrlComment
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import repositories.IRepo

fun Application.configureRouting(rep: IRepo) {
    // Starting point for a Ktor app:
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/nome/{nome}") {
            call.respondText("Hello, ${call.parameters["nome"]}")
        }

        get("/anime/{id}") {
            val ctrl = CtrlAnime(rep)
            call.respondText("${ctrl.getAnimeById(call.parameters["id"])}")
        }

        post("/criar-anime") {
            val request = call.receive<String>()
            val ctrl = CtrlAnime(rep)
            call.respondText(ctrl.createAnime(request))
        }

        post("/atualizar-anime") {
            val request = call.receive<String>()
            val ctrl = CtrlAnime(rep)
            call.respondText(ctrl.updateAnime(request))
        }

        post("/deletar-anime") {
            val request = call.receive<String>()
            val ctrl = CtrlAnime(rep)
            println(request.toInt())
            call.respondText(ctrl.deleteAnime(request.toInt()))
        }

        get("/todos-animes"){
            val ctrl = CtrlAnime(rep)
            call.respondText(ctrl.getAllAnimes().toString())

        }

        get("/comentarios/{id}") {
            val ctrl = CtrlComment(rep)
            val id = Integer.valueOf(call.parameters["id"])
            call.respondText(ctrl.getAllCommentsByReview(id))
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
