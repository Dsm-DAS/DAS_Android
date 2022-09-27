package Request

data class SignupRequest(val email : String, val password : String, val name : String, val grade : Int, val classNum : Int, val number : Int)
