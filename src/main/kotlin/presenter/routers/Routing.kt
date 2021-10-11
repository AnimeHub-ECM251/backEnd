package presenter.routers

import Controllers.CtrlAnime
import ch.qos.logback.core.db.dialect.MySQLDialect
import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.request.*
import io.ktor.response.*
import models.Anime
import repositories.mysql.RepoMysql
import usecases.UcAnime

fun Application.configureRouting() {
    // Starting point for a Ktor app:
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/nome/{nome}") {
            call.respondText("Hello, ${call.parameters["nome"]}")
        }

        post("/criar-anime") {
            val request = call.receive<String>()
            val rep = RepoMysql()
            val ctrl = CtrlAnime(rep)
            ctrl.createAnime(request)

        }
    }

}
