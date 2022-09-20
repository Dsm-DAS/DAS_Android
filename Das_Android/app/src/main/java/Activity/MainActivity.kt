package Activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import org.techtown.das_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}