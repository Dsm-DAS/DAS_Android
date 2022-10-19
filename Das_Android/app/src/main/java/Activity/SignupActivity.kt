package Activity

import Api.ApiProvider
import Base.BaseActivity
import Request.EmailRequest
import Request.SignupRequest
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import org.techtown.das_android.R
import org.techtown.das_android.databinding.ActivitySignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupActivity : BaseActivity<ActivitySignupBinding>(
    R.layout.activity_signup
) {
    companion object {
        var DSMClass: String = ""
        var DSMGrade: String = ""
    }

    override fun initView() {
        /*binding.ivSignupPwprecautions.setOnClickListener {
=======
      binding.ivSignupEmailprecautions.setOnClickListener {
          if (binding.textView5.getVisibility() == View.INVISIBLE) {
              binding.textView5.setVisibility(View.VISIBLE)
          } else {
              binding.textView5.setVisibility(View.INVISIBLE)
          }
      }*/

        /*binding.ivSignupEmailprecautions.setOnClickListener {
            if (binding.textView4.getVisibility() == View.INVISIBLE) {
                binding.textView4.setVisibility(View.VISIBLE)
            } else {
                binding.textView4.setVisibility(View.INVISIBLE)
            }
        }*/



        initGradeSpinner()         // 학년
        initClassSpinner()     // 반
        initSpinnerHandler()       // 번호


        binding.btnVerifyEmail.setOnClickListener {
            checkSignUp()               // 회원가입 글자 체크
        }

        binding.btnSignupBack.setOnClickListener {
            finish()                // 뒤로가기
        }

        binding.btnVerifyEmail.setOnClickListener {
            checkEmail()
        }

        /*binding.etSignupEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                val length = binding.etSignupEmail.text.length
                if (length >= 1) {
                    binding.tvSignupEmailCheck.setBackgroundColor(Color.parseColor("#FB5B93"))
                } else {
                    binding.tvSignupEmailCheck.setBackgroundColor(Color.parseColor("#FFCCDE"))
                }
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })*/
    }

    private fun checkEmail() {
        val request = EmailRequest(binding.etSignupEmail.toString())

        ApiProvider.retrofit.email(request).enqueue(object : Callback<Void>{
            override fun onResponse(call: Call<Void>, response: Response<Void>) {

            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(applicationContext, "이메일을 다시 확인해 주세여", Toast.LENGTH_SHORT).show()
            }

        })
    }


    private fun checkSignUp() {
        val email = binding.etSignupEmail.text.length
        val name = binding.etSignupName.text.length
        val password = binding.etSignupPassword.text.length

        if (email == 0 && name == 0 && password == 0) {
            Toast.makeText(applicationContext, "모든 항목을 입력해주세요", Toast.LENGTH_SHORT).show()
        } else {
            signUp()
        }
    }


    private fun signUp() {
        val email: String = binding.etSignupEmail.text.toString()
        val name: String = binding.etSignupName.text.toString()
        val password: String = binding.etSignupPassword.text.toString()
        val grade = DSMGrade.toString().toInt()
        val classNum = DSMClass.toString().toInt()
        val number: Int = binding.etSignupNumber.toString().toInt()

        val signupRequest = SignupRequest(email, password, name, grade, classNum, number)

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
                Toast.makeText(applicationContext, "서버 켜...", Toast.LENGTH_SHORT).show()

            }

        })


    }


    private fun initGradeSpinner() {
        val grade = resources.getStringArray(R.array.grade)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, grade)
        binding.spinnerSignupGrade.adapter = adapter
    }

    private fun initClassSpinner() {
        val classes = resources.getStringArray(R.array.classes)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, classes)
        binding.spinnerSignupClass.adapter = adapter
    }

    private fun initSpinnerHandler() {
        binding.spinnerSignupGrade.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    DSMGrade = binding.spinnerSignupGrade.selectedItem.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }
            }
        binding.spinnerSignupClass.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    DSMClass = binding.spinnerSignupClass.selectedItem.toString()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {

                }

            }
    }


    override fun observeEvent() {

    }
}