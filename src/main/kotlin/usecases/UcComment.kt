package usecases

import models.Comment
import repositories.IRepo

class UcComment (rep: IRepo){
    val repo: IRepo

    init {
        this.repo = rep
    }

    fun create(comment: Comment){
        this.repo.create("Comment", comment.toHashMap())
    }

    fun update(comment: Comment){
        this.repo.update("Comment", comment.toHashMap())
    }

    fun delete(id: Int) {
        this.repo.delete("Comment", id)
    }

    fun get(id: String?): Comment {
        val map = this.repo.getById("Comment", id)
        val comment = Comment.fromHashMap(map as HashMap<String, String>)
        return comment
    }

    fun getAllCommentsByReview(idAnime: Int?): List<Comment> {
        val list = this.repo.getAll("Comment","idAnime = '${idAnime}'")
        val comments = ArrayList<Comment>()
        for (i in list.indices) {
            val map = list[i] as HashMap<String, String>
            val comment = Comment.fromHashMap(map)
            if (comment.idAnime == idAnime) {
                comments.add(comment)
            }
        }
        return comments

    }


}