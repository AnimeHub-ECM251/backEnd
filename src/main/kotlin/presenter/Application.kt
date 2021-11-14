package presenter

import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import kotlinx.coroutines.time.delay
import presenter.routers.*
import repositories.IRepo
import repositories.mysql.RepoMysql
import java.util.*
import java.util.concurrent.TimeUnit
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
            TimeUnit.SECONDS.sleep(5)
        }
    }



    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
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
