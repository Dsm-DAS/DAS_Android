package Activity

import Api.ApiProvider
import Request.SignupRequest
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import org.techtown.das_android.R
import org.techtown.das_android.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Response

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private var _binding:ActivitySignupBinding? = null
    companion object {
        var DSMClass : String = ""
        var DSMGrade : String = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        if(binding.ivSignupEmailprecautions.getVisibility() == View.INVISIBLE){
//                binding.textView4.setVisibility(View.VISIBLE)
//        }
//

        binding.ivSignupEmailprecautions.setOnClickListener {
            if (binding.textView4.getVisibility() == View.INVISIBLE){
            binding.textView4.setVisibility(View.VISIBLE)
            }
            else
            {
                binding.textView4.setVisibility(View.INVISIBLE)
            }
        }


        setupSpinnerGrade()         // 학년
        setupSpinnerClass_Num()     // 반
        setupSpinnerHandler()       // 번호


        binding.tvSignup.setOnClickListener{
            SignupCheck()               // 회원가입 글자 체크
        }

        binding.back.setOnClickListener{
            finish()                // 뒤로가기
        }

        binding.etSignupEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val length = binding.etSignupEmail.text.length
                if (length >=  1){
                    binding.tvSignupEmailCheck.setBackgroundColor(Color.parseColor("#FB5B93"))
                }
                else {
                    binding.tvSignupEmailCheck.setBackgroundColor(Color.parseColor("#FFCCDE"))
                }
            }


            override fun afterTextChanged(p0: Editable?) {

            }
        })

    }




    private fun SignupCheck(){
        val email = binding.etSignupEmail.text.length
        val name =  binding.etSignupName.text.length
        val password = binding.etSignupPw.text.length

        if (email == 0 && name == 0 && password == 0){
            Toast.makeText(applicationContext,"모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show()
        }
        else{
            Signup()
        }
    }



    private fun Signup() {
        val email: String = binding.etSignupEmail.text.toString()
        val name: String = binding.etSignupName.text.toString()
        val password: String = binding.etSignupPw.text.toString()
        val grade = DSMGrade.toString().toInt()
        val classNum = DSMClass.toString().toInt()
        val number: Int = binding.etSignupNumber.toString().toInt()

        val signupRequest = SignupRequest(email,password, name, grade, classNum, number)

        ApiProvider.retrofit.signup(signupRequest).enqueue(object : retrofit2.Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "회원가입이완료되었습니다, 로그인 후 이용해주세요",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(applicationContext,"서버 켜...", Toast.LENGTH_SHORT).show()

            }

        })


    }




    private fun setupSpinnerGrade() {
        val grade = resources.getStringArray(R.array.grade)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,grade)
        binding.spinnerGrade.adapter = adapter
    }

    private fun setupSpinnerClass_Num() {
        val classes = resources.getStringArray(R.array.classes)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,classes)
        binding.spinnerClass.adapter = adapter
    }

    private fun setupSpinnerHandler(){
        binding.spinnerGrade.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                DSMGrade = binding.spinnerGrade.selectedItem.toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
        binding.spinnerClass.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                DSMClass = binding.spinnerClass.selectedItem.toString()
//                DSMClass = Integer.parseInt(binding.spinnerGrade.selectedItem.toString())
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}