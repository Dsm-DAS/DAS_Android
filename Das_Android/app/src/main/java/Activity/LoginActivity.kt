package Activity

import Api.ApiProvider
import Base.BaseActivity
import Request.LoginRequest
import Response.LoginResponse
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import org.techtown.das_android.R
import org.techtown.das_android.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : BaseActivity<ActivityLoginBinding>(
    R.layout.activity_login
)
{

    companion object {
        var refreshToken : String = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTvLoginGoToSignUp()
        initTvLoginGoToLogin()
    }

    private fun initTvLoginGoToLogin() {
        binding.btnLoginLogin.setOnClickListener {
            val request = LoginRequest(binding.etLoginEmail.toString(),binding.etLoginPassword.toString())

            ApiProvider.retrofit.login(request).enqueue(object: Callback<LoginResponse>{
                override fun onResponse(
                    call: Call<LoginResponse>,
                    response: Response<LoginResponse>
                ) {
                    val intent = Intent(this@LoginActivity,MainActivity::class.java)
                    startActivity(intent)
                    refreshToken = response.body()!!.refresh_token
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    Toast.makeText(this@LoginActivity,"실패",Toast.LENGTH_SHORT).show()
                }

            })
        }
    }

    private fun initTvLoginGoToSignUp() {
        binding.tvLoginGoToSignUp.setOnClickListener {
            startActivity(Intent(baseContext, SignupActivity::class.java))
        }
    }

    override fun initView() {

    }

    override fun observeEvent() {
    }
}