package models

import com.google.gson.Gson
import models.errors.INTANCE_PROPERTIES_DONT_MATCH

data class User(
    var login: String,
    var password: String,
    var email: String,
    var profilePicture: String,
    var role: Int?,
    val id: Int?
) : ITable {

    override fun toHashMap(): HashMap<String, String> {
        return hashMapOf(
            "id" to id.toString(),
            "login" to login,
            "email" to email,
            "password" to password,
            "profilePicture" to profilePicture,
            "role" to role.toString()
        )
    }

    companion object {
        val defaultProfilePicture = "https://www.gravatar.com/avatar/00000000000000000000000000000000?d=mp&f=y"
        val defaultRole = 1
        private val properties : List<String> = listOf("login", "email")

        fun fromHashMap(map: HashMap<String, String>) : User{
            val mapKeys = map.keys.toList()
            if (mapKeys.containsAll(User.properties)) {
                return User(
                    login = map["login"]!!,
                    email = map["email"]!!,
                    profilePicture = map["profilePicture"] ?: defaultProfilePicture,
                    role = map["role"]?.toInt() ?: defaultRole,
                    id = map["id"]?.toInt(),
                    password = map["password"] ?: "-"
                )
            } else throw INTANCE_PROPERTIES_DONT_MATCH()
        }
    }

    override fun toString(): String {
        val map = hashMapOf("login" to this.login, "profilePicture" to this.profilePicture)
        return Gson().toJson(map);
    }
}