package Request

data class EmailCheckRequest(
    val email: String,
    val code: String
)