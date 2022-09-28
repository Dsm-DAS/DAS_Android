package Activity

import Request.SignupRequest
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import org.techtown.das_android.R
import org.techtown.das_android.databinding.ActivitySignupBinding

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private var _binding:ActivitySignupBinding? = null
    companion object {
        var hisClasses : String = ""
        var hisGrade : String = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //binding.tvSignup.setOnClickListener{
        //}

        setupSpinnerGrade()
        setupSpinnerClass()
        setupSpinnerHandler()
    }
    private fun setupSpinnerGrade() {
        val grade = resources.getStringArray(R.array.grade)
        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,grade)
        binding.spinnerGrade.adapter = adapter
    }

    private fun setupSpinnerClass() {
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
                hisGrade = binding.spinnerGrade.selectedItem.toString()
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
                hisClasses = binding.spinnerClass.selectedItem.toString()
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