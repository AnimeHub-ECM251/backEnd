package presenter

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import presenter.routers.*
import repositories.IRepo
import repositories.mysql.RepoMysql


fun main() {
    val repo : IRepo = RepoMysql()

    embeddedServer(Netty, port = 8081, host = "0.0.0.0") {
        configureRouting(repo)
    }.start(wait = true)
}
