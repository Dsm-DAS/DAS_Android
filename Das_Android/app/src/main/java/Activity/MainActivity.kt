package Activity

import Fragment.DocumentFragment
import Fragment.HomeFragment
import Fragment.MainpageFragment
import Fragment.SearchFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import org.techtown.das_android.R
import org.techtown.das_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navigationItemSelect()
    }


    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.frame, fragment)
            .commit()
    }

    fun addFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction()
            .add(R.id.frame,fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun navigationItemSelect() {
        binding.bottomNavigationView.run {
            setOnItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.main -> replaceFragment(MainpageFragment())
                    R.id.text -> replaceFragment(DocumentFragment())
                    R.id.search -> replaceFragment(SearchFragment())
                    R.id.menu -> replaceFragment(HomeFragment())
                }
                true
            }
            selectedItemId = R.id.main
        }
    }
}