package controllers

import com.google.gson.Gson
import controllers.errors.RATING_OUT_OF_RANGE
import models.Rating
import models.Watch_List
import repositories.IRepo
import usecases.UcRating
import usecases.UcWatch_List

class CtrlRating(rep: IRepo) {
    val repo : IRepo
    val ucRating : UcRating

    init {
        repo = rep
        ucRating = UcRating(rep)
    }

    fun insert(body: String): String {
        var map: HashMap<String, String> = HashMap()
        map = Gson().fromJson(body, map.javaClass)
        val r : Rating = Rating.fromHashMap(map)
        if (r.rating in 0..5) {
            return ucRating.insert(r)
        } else {
            throw RATING_OUT_OF_RANGE()
        }
    }

    fun getUserRating(userId: Int, animeId: Int): String {
        val rating = ucRating.getUserRating(userId, animeId)
        val map : HashMap<String, String>
        map = if (rating != -1) {
            hashMapOf<String, String>(
                "rated" to "true",
                "rating" to rating.toString()
            )
        } else {
            hashMapOf<String, String>(
                "rated" to "false",
                "rating" to "-1"
            )
        }
        return Gson().toJson(map)
    }

}