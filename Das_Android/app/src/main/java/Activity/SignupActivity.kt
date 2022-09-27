package Activity

import Request.SignupRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.techtown.das_android.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvSignup.setOnClickListener{

        }


    }
}