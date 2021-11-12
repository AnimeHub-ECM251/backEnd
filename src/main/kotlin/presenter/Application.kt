package presenter

import io.ktor.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import presenter.routers.*
import repositories.IRepo
import repositories.mysql.RepoMysql
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.HttpHeaders.AccessControlAllowHeaders


fun main() {
    println("Servidor sendo iniciado!!!!@!!!!!!!")

    val repo : IRepo = RepoMysql()

    embeddedServer(Netty, port = 8081, host = "0.0.0.0") {
        install(CORS) {
            method(HttpMethod.Options)
            anyHost()
        }
        install(DefaultHeaders) {
            header(HttpHeaders.AcceptCharset, "utf-8")
        }
        configureRouting(repo)
    }.start(wait = true)
}
