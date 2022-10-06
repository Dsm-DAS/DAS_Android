package Activity

import Base.BaseActivity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels

class LoginActivity : BaseActivity<>(

) {
    private val binding by viewModels<LoginActivity> {  }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(binding.root)

        binding.tvGoSignup.setOnClickListener{
            val intent = Intent(applicationContext, SignupActivity::class.java)
            startActivity(intent)
        }
    }
}