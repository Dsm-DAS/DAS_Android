package sample.view

import Base.BaseActivity
import android.widget.TextView
import androidx.activity.viewModels
import org.techtown.das_android.R
import org.techtown.das_android.databinding.ActivitySampleBinding
import sample.model.SampleModel
import sample.viewmodel.SampleViewModel

class SampleActivity : BaseActivity<ActivitySampleBinding>(R.layout.activity_sample) {

   private val sampleViewModel: SampleViewModel by viewModels()

    override fun initView() {
        binding.run {
            btn.setOnClickListener {
                sampleViewModel.changeGrade(
                    SampleModel(
                        grade = textToInt(etGrade),
                        class_num = textToInt(etClass)
                    )
                )
            }
        }
    }

    override fun observeEvent() {
        sampleViewModel.run {
            grade.observe(this@SampleActivity){
                binding.tv.text = it.toString()
            }
            class_num.observe(this@SampleActivity){
                showToast(it.toString())
            }
        }
    }

    private fun textToInt(textView: TextView): Int =
        textView.text.toString().toInt()

}