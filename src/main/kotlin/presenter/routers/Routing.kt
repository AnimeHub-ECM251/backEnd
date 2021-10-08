package presenter.routers

import io.ktor.routing.*
import io.ktor.application.*
import io.ktor.response.*

fun Application.configureRouting() {
    // Starting point for a Ktor app:
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        get("/nome/{nome}") {
            call.respondText("Hello, ${call.parameters["nome"]}")
        }
    }

}
