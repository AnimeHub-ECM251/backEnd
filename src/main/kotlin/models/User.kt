package models

import models.errors.INTANCE_PROPERTIES_DONT_MATCH

data class User(
    var login: String,
    var password: String?,
    var email: String,
    var profilePicture: String,
    var role: Int,
    val id: Int?
) : ITable {

    override fun toHashMap(): HashMap<String, String> {
        return hashMapOf(
            "id" to id.toString(),
            "login" to login,
            "email" to email,
            "profilePicture" to profilePicture,
            "role" to role.toString()
        )
    }

    companion object {
        private val properties : List<String> = listOf("login", "email", "profilePicture", "role")

        fun fromHashMap(map: HashMap<String, String>) : User{
            val mapKeys = map.keys.toList()
            if (mapKeys.containsAll(User.properties)) {
                val user = User(login=map["login"]!!, email=map["email"]!!, profilePicture=map["profilePicture"]!!,
                                role=map["role"]!!.toInt(), id=map["id"]?.toInt(), password=map["password"]?.toString())
                return user

            } else throw INTANCE_PROPERTIES_DONT_MATCH()

        }
    }


}