package repositories.mysql

import models.Anime
import repositories.IRepo
import repositories.errors.DATABASE_CONNECTION_FAILED
import util.DBTablePrinter
import java.sql.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType


class RepoMysql (dbName: String = "AnimeHubDB") : IRepo{
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
        if (id.next()) {
            val idNumber = id.getObject("id") as Int?
            return idNumber?: -1
        }
        return -1

    }

    override fun getById(table: String, id: String?): Map<String, String>? {
        val result: ResultSet = this.SQLStatement.executeQuery("SELECT * FROM ${this.DBName}.${table} WHERE id = '${id}'")
        val map = resultSetToList(result)
        return map[0]
    }

    override fun getAll(table: String, where: String): List<Any> {
        val result: ResultSet = this.SQLStatement.executeQuery("SELECT * FROM ${this.DBName}.${table} WHERE ${where}")
        val map = resultSetToList(result)
        return map
    }



}

fun main(){
    val repo = RepoMysql()
    println(repo.getAll("Anime"))
    repo.read("Anime")
}