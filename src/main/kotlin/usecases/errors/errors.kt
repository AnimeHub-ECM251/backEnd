package usecases.errors

import java.lang.Exception

class USER_ALREADY_EXISTS: Exception("Username already exists")