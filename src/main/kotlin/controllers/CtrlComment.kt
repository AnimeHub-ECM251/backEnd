package controllers

import com.google.gson.Gson
import models.Anime
import models.Comment
import repositories.IRepo
import repositories.mysql.RepoMysql
import usecases.UcAnime
import usecases.UcComment
import usecases.UcUser

class CtrlComment (rep:IRepo){
    private val repo : IRepo
    private val ucComment : UcComment

    init {
        repo = rep
        ucComment = UcComment(rep)
    }

    fun createComment(body: String): String {
        var map: HashMap<String, String> = HashMap()
        map = Gson().fromJson(body, map.javaClass)
        val comment : Comment = Comment.fromHashMap(map)
        ucComment.create(comment)
        return "Comment ${comment.text} was created"
    }

    fun updateComment(body: String): String {
        var map: HashMap<String, String> = HashMap()
        map = Gson().fromJson(body, map.javaClass)
        val comment : Comment = Comment.fromHashMap(map)
        ucComment.update(comment)
        return "Comment ${comment.id} was updated"
    }

    fun getAllCommentsByReview(id: Int?): String {
        val ucUser = UcUser(repo)
        val comments = ucComment.getAllCommentsByReview(id)
        val commentsMap : MutableList<HashMap<String,String>> = mutableListOf()
        comments.forEach { comment ->
            val username = ucUser.getUserById(comment.idUser).login
            val commentMap = comment.toHashMap()
            commentMap["username"] = username.toString()
            commentsMap.add(commentMap)
        }
        return Gson().toJson(commentsMap)
    }

}




