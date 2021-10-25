package usecases

import models.Anime
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
}