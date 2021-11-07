package controllers

import com.google.gson.Gson
import models.Comment
import models.User
import repositories.IRepo
import usecases.UcComment
import usecases.UcUser

class CtrlUser (rep:IRepo) {
    private val repo : IRepo
    private val ucUser : UcUser

    init {
        repo = rep
        ucUser = UcUser(repo)
    }

    fun create(body: String): String {//TODO fazer
        var map: HashMap<String, String> = HashMap()
        map = Gson().fromJson(body, map.javaClass)
        val comment : Comment = Comment.fromHashMap(map)
        //ucComment.create(comment)
        return "User ${comment.text} was created"
    }

    fun update(body: String): String { //TODO fazer
        var map: HashMap<String, String> = HashMap()
        map = Gson().fromJson(body, map.javaClass)
        val comment : Comment = Comment.fromHashMap(map)
        //ucComment.update(comment)
        return "User ${comment.id} was updated"
    }

    fun getUserById(id: Int): User {
        return ucUser.getUserById(id.toString())

    }


}