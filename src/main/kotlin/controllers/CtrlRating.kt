package controllers

import com.google.gson.Gson
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
        if (r.rating >= 0 && r.rating <= 5) {
            return ucRating.insert(r)
        } else {
            return "Rating must be between 0 and 5"
        }

    }



}