package Base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import org.techtown.das_android.databinding.ActivitySampleBinding

abstract class BaseActivity<B : ViewDataBinding>(
    @LayoutRes private val layoutId: Int
) : AppCompatActivity() {

//    protected val binding: B by lazy {
//        DataBindingUtil.setContentView(this, layoutId)
//    }

    protected lateinit var binding: B

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,layoutId)
        binding.lifecycleOwner = this

        initView()

        observeEvent()
    }
    
    fun showToast(msg:String){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    abstract fun initView()

    abstract fun observeEvent()

}