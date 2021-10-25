package controllers

import com.google.gson.Gson
import models.Anime
import models.Comment
import repositories.IRepo
import usecases.UcComment

class CtrlComment (rep:IRepo){
    private val repo : IRepo
    private val ucComment : UcComment

    init {
        repo = rep
        ucComment = UcComment(rep)
    }

    fun create(body: String): String {
        var map: HashMap<String, String> = HashMap()
        map = Gson().fromJson(body, map.javaClass)
        val comment : Comment = Comment.fromHashMap(map)
        ucComment.create(comment)
        return "Comment ${comment.text} was created"
    }

    fun update(body: String): String {
        var map: HashMap<String, String> = HashMap()
        map = Gson().fromJson(body, map.javaClass)
        val comment : Comment = Comment.fromHashMap(map)
        ucComment.update(comment)
        return "Comment ${comment.id} was updated"
    }
}