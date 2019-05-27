package azamat.kz.loginapp

import java.util.regex.Pattern

val EMPTY_EMAIL = 1
val NOT_VALID_EMAIL = 2
val EMPTY_PASSWORD = 3
val NOT_VALID_PASSWORD = 4
val SUCCESS = 5

fun isValidPassword(password: String): Boolean {

    val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=\\S+$).{4,}$"
    val pattern = Pattern.compile(PASSWORD_PATTERN)
    val matcher = pattern.matcher(password)

    return matcher.matches()
}