package repositories.mysql

import repositories.errors.DATABASE_CONNECTION_FAILED
import util.DBTablePrinter
import java.sql.*

class RepoMysql (dbName: String = "animeHubDB"){
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


    fun read(table: String, where: String="TRUE"){
        var result: ResultSet = this.SQLStatement.executeQuery("SELECT * FROM ${this.DBName}.${table} WHERE ${where}")
        DBTablePrinter.printResultSet(result)
    }

    fun create(table: String, data: HashMap<String, String>){
        executeStatement("INSERT INTO ${this.DBName}.${table} (${data.keys.joinToString { it -> it }}) values (${data.values.joinToString { it -> "\'${it}\'" }});")


    }

    fun update(table: String, data: HashMap<String, String>){
        val id = data.remove("id")
        executeStatement("UPDATE ${this.DBName}.${table} SET ${data.keys.joinToString { it -> "$it = \'${data[it]}\'" }} WHERE id = $id")
    }

    fun delete(table: String, id: Int){
        executeStatement("DELETE FROM ${this.DBName}.${table} WHERE id = $id")
    }

}
