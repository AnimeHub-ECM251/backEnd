package controllers

import com.google.gson.Gson
import controllers.errors.PASSWORD_NOT_HASHED
import models.Comment
import models.User
import repositories.IRepo
import usecases.UcComment
import usecases.UcUser
import usecases.errors.USER_ALREADY_EXISTS

class CtrlUser (rep:IRepo) {
    private val repo : IRepo
    private val ucUser : UcUser

    init {
        repo = rep
        ucUser = UcUser(repo)
    }

    fun createUser(body: String): String {
        var map: HashMap<String, String> = HashMap()
        map = Gson().fromJson(body, map.javaClass)
        val user : User = User.fromHashMap(map)
        if (user.password.length != 64) throw PASSWORD_NOT_HASHED()
        ucUser.createUser(user)
        return "User ${user.login} was created"
    }

    fun login(body: String): String {
        var map: HashMap<String, String> = HashMap()
        map = Gson().fromJson(body, map.javaClass)
        return ucUser.login(map["login"]?:"-1", map["password"]?:"-1").toString()
    }

    fun getUserById(id: Int): User {
        return ucUser.getUserById(id)

    }

}