package Api

import Request.LoginRequest
import Request.SignupRequest
import Response.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {

    @POST("user/login")
    fun login(
        @Body request : LoginRequest
    ) : Call<LoginResponse>

    @POST("user/signup")
    fun signup(
        @Body request : SignupRequest
    ) : Call<Void>
}