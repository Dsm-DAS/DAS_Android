package Api

import Request.EmailCheckRequest
import Request.EmailRequest
import Request.LoginRequest
import Request.SignupRequest
import Response.LoginResponse
import retrofit2.Call
import retrofit2.http.*

interface UserApi {

    @POST("user/login")
    fun login(
        @Body request : LoginRequest
    ) : Call<LoginResponse>

    @POST("user/signup")
    fun signup(
        @Body request : SignupRequest
    ) : Call<Void>

    @POST("user/email")
    fun email(
        @Body request: EmailRequest
    ) : Call<Void>

    @PUT("user/email")
    fun emailCheck(
        @Body request: EmailCheckRequest
    ) : Call<Void>

    @PATCH("user/token")
    fun token(
        @Header("Authorization") String : String
    ) : Call<LoginResponse>
}