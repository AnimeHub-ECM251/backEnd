package repositories.mysql

import models.Anime
import repositories.IRepo
import repositories.errors.DATABASE_CONNECTION_FAILED
import util.DBTablePrinter
import java.sql.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


class RepoMysql (dbName: String = "AnimeHubDB") : IRepo{
    private var connection: Connection
    private var SQLStatement: Statement
    private val DBName: String

    companion object {
        val url = "${System.getenv("DATABASE_URL") ?: "localhost"}"
        val port = 3306
    }

    init {
        this.connection = createConnection()
        this.SQLStatement = createStatement()
        this.DBName = dbName
    }

    private fun createConnection(): Connection {
        try {
            val c  = DriverManager.getConnection(
                "jdbc:mysql://$url:$port/?characterEncoding=utf8&useUnicode=true",
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

    private fun checkConnection(){
        try {
            if (connection.isClosed) {
                connection = createConnection()
            }
        }
        catch (e: SQLException){
            e.printStackTrace()
            throw DATABASE_CONNECTION_FAILED()
        }
    }

    /**
     * Fonte: https://gist.github.com/cworks/4175942
     */
    @Throws(SQLException::class)
    private fun resultSetToList(rs: ResultSet): MutableList<Map<String, String>> {
        val md = rs.metaData
        val columns = md.columnCount
        val rows: MutableList<Map<String, String>> = ArrayList()
        while (rs.next()) {
            val row: MutableMap<String, String> = HashMap(columns)
            for (i in 1..columns) {
                row[md.getColumnName(i)] = rs.getString(i)
            }
            rows.add(row)
        }
        return rows
    }

    private fun createStatement(): Statement {
        checkConnection()
        return this.connection.createStatement()
    }

    private fun executeStatement(query: String) {
        checkConnection()
        if (this.SQLStatement.isClosed){
            this.SQLStatement = createStatement()
        }
        val preparedStatement = this.connection.prepareStatement(query)
        preparedStatement!!.executeUpdate()

    }

    override fun read(table: String, where: String){
        checkConnection()
        var result: ResultSet = this.SQLStatement.executeQuery("SELECT * FROM ${this.DBName}.${table} WHERE ${where}")
        DBTablePrinter.printResultSet(result)
    }

    override fun create(table: String, data: HashMap<String, String>){
        checkConnection()
        data.remove("id")
        executeStatement("INSERT INTO ${this.DBName}.${table} (${data.keys.joinToString { it -> it }}) values (${data.values.joinToString { it -> "\'${it}\'" }});")

    }

    override fun update(table: String, data: HashMap<String, String>){
        checkConnection()
        val id = data.remove("id")
        executeStatement("UPDATE ${this.DBName}.${table} SET ${data.keys.joinToString { it -> "$it = \'${data[it]}\'" }} WHERE id = $id")
    }

    override fun delete(table: String, id: Int){
        checkConnection()
        executeStatement("DELETE FROM ${this.DBName}.${table} WHERE id = $id")
    }

    override fun getId(table: String, column: String, value: String): Int {
        checkConnection()
        val id: ResultSet = this.SQLStatement.executeQuery("SELECT id FROM ${this.DBName}.${table} WHERE ${column} = '${value}'")
        if (id.next()) {
            val idNumber = id.getObject("id") as Int?
            return idNumber?: -1
        }
        id.close()
        return -1

    }

    override fun getById(table: String, id: Int): Map<String, String>? {
        checkConnection()
        val result: ResultSet = this.SQLStatement.executeQuery("SELECT * FROM ${this.DBName}.${table} WHERE id = '${id}'")
        val map = resultSetToList(result)
        result.close()
        return map[0]
    }

    override fun getAll(table: String, where: String): List<Map<String,String>> {
        checkConnection()
        val result: ResultSet = this.SQLStatement.executeQuery("SELECT * FROM ${this.DBName}.${table} WHERE ${where}")
        val map = resultSetToList(result)
        result.close()
        return map
    }

}
