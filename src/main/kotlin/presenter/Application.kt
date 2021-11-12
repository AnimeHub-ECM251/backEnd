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
import kotlinx.coroutines.delay
import java.util.*
import kotlin.concurrent.schedule


fun main() {
    println("Servidor sendo iniciado!!!!!!!!!!!")

    var repo : IRepo

    while (true) {
        try {
            repo = RepoMysql()
            break;
        } catch (e : Exception) {
            println("Não foi possível se conectar com o DB: ${e}")
            println("Tentando conexão novamente em 5 segundos...")
            Timer().schedule(5000) {
                println("Tentando novamente...")
            }
        }
        }



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
