package controllers.errors

import java.lang.Exception

class RATING_OUT_OF_RANGE: Exception("Rating must be between 0 and 5!")
class PASSWORD_NOT_HASHED: Exception("Password must be hashed! (should have 64 characters)")
