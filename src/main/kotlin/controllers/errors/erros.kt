package controllers.errors

import java.lang.Exception

class RATING_OUT_OF_RANGE: Exception("Rating must be between 0 and 5!")