package Activity

import Base.BaseActivity
import android.content.Intent
import android.os.Bundle
import org.techtown.das_android.R
import org.techtown.das_android.databinding.ActivityLoginBinding

class LoginActivity : BaseActivity<ActivityLoginBinding>(
    R.layout.activity_login
) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initTvLoginGoToSignUp()
    }

    private fun initTvLoginGoToSignUp() {
        binding.tvLoginGoToSignUp.setOnClickListener {
            startActivity(Intent(baseContext, SignupActivity::class.java))
        }
    }
}