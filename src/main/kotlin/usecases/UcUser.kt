package usecases

import models.User
import repositories.IRepo

class UcUser (rep: IRepo) {
    val repo: IRepo

    init {
        this.repo = rep
    }

    fun createUser(user: User){
        this.repo.create("User", user.toHashMap())
    }

    fun updateUser(user: User){
        this.repo.update("User", user.toHashMap())
    }

    fun deleteUser(id: Int) {
        this.repo.delete("user", id)
    }

    fun getUserById(id: String?): User {
        val map = this.repo.getById("User", id)
        val user = User.fromHashMap(map as HashMap<String, String>)
        return user
    }

    fun getAllAUser(): List<User> {
        val list = this.repo.getAll("User")
        val userList = ArrayList<User>()
        for (map in list) {
            userList.add(User.fromHashMap(map as HashMap<String, String>))
        }
        return userList
    }
}