package repositories

import util.DBTablePrinter
import java.sql.ResultSet

interface IRepo {

    fun read(table: String, where: String = "True")

    fun create(table: String, data: HashMap<String, String>)

    fun update(table: String, data: HashMap<String, String>)

    fun delete(table: String, id: Int)
}