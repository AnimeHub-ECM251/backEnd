package repositories.mysql

import ch.qos.logback.core.db.dialect.MySQLDialect
import repositories.IRepo
import repositories.errors.DATABASE_CONNECTION_FAILED
import util.DBTablePrinter
import java.sql.*

class RepoMysql (dbName: String = "animeHubDB") : IRepo{
    private val connection: Connection
    private val SQLStatement: Statement
    private val DBName: String

    init {
        this.connection = createConnection()
        this.SQLStatement = createStatement()
        this.DBName = dbName
    }

    private fun createConnection(): Connection {
        try {
            val c  = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306",
                "root",
                "password"
            )
            println("Connection created")
            return c
        }
        catch (e: SQLException){
            e.printStackTrace()
            throw DATABASE_CONNECTION_FAILED()
        }
    }

    private fun createStatement(): Statement {
        return this.connection!!.createStatement()
    }

    private fun executeStatement(query: String) {
        try {
            val preparedStatement = this.connection!!.prepareStatement(query)
            preparedStatement!!.executeUpdate()
        }
        catch (e: SQLException){
            println("Not possible to execute update: ${e.message}")
        }

    }


    override fun read(table: String, where: String){
        var result: ResultSet = this.SQLStatement.executeQuery("SELECT * FROM ${this.DBName}.${table} WHERE ${where}")
        DBTablePrinter.printResultSet(result)

    }

    override fun create(table: String, data: HashMap<String, String>){
        data.remove("id")
        executeStatement("INSERT INTO ${this.DBName}.${table} (${data.keys.joinToString { it -> it }}) values (${data.values.joinToString { it -> "\'${it}\'" }});")

    }

    override fun update(table: String, data: HashMap<String, String>){
        val id = data.remove("id")
        executeStatement("UPDATE ${this.DBName}.${table} SET ${data.keys.joinToString { it -> "$it = \'${data[it]}\'" }} WHERE id = $id")
    }

    override fun delete(table: String, id: Int){
        executeStatement("DELETE FROM ${this.DBName}.${table} WHERE id = $id")
    }

    override fun getId(table: String, column: String, value: String): Int {
        val id: ResultSet = this.SQLStatement.executeQuery("SELECT id FROM ${this.DBName}.${table} WHERE ${column} = '${value}'")
        id.next()
        return id.getObject("id") as Int
    }

}

fun main(){
    val repo = RepoMysql()
    repo.getId("Anime", "title", "Konosuba5")
}