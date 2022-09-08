package Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.techtown.das_android.R

class MainActivity {

    class MainActivity : AppCompatActivity() {
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
        }
    }
}