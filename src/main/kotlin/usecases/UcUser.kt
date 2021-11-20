package usecases

import models.User
import repositories.IRepo
import usecases.errors.USER_ALREADY_EXISTS

class UcUser (rep: IRepo) {
    val repo: IRepo

    init {
        this.repo = rep
    }

    fun createUser(user: User) {

        val user_ = getUserByLogin(user.login)
        if (user_ == null) {
            this.repo.create("User", user.toHashMap())
        } else {
            throw USER_ALREADY_EXISTS()
        }
    }

    fun updateUser(user: User){
        this.repo.update("User", user.toHashMap())
    }

    fun deleteUser(id: Int) {
        this.repo.delete("user", id)
    }

    fun getUserById(id: Int): User {
        val map = this.repo.getById("User", id)
        val user = User.fromHashMap(map as HashMap<String, String>)
        return user
    }

    fun getUserByLogin(login: String?): User? {
        val id = this.repo.getId("User", "login", login.toString())
        return if (id != -1) this.getUserById(id) else null
    }

    fun login(login: String, password: String): Int {
        val user = this.getUserByLogin(login)
        if (user != null && user.password == password) {
            return user.id!!
        }
        return -1
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