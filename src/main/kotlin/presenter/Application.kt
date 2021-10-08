package presenter

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import presenter.routers.*


fun main() {
    embeddedServer(Netty, port = 8081, host = "0.0.0.0") {
        configureRouting()
    }.start(wait = true)
}
