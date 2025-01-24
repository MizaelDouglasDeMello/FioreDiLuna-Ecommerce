package br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.activities

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.mizaeldouglas.fiorediluna_ecommerce.R
import br.com.mizaeldouglas.fiorediluna_ecommerce.databinding.ActivityHomeBinding
import br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.fragments.HomeFragment
import br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.fragments.ProfileFragment
import br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.fragments.SearchFragment
import br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.viewmodels.SharedViewModel

class HomeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializer()
    }

    private fun initializer() {
        setupViewModel()
        setupBottomNavigation()
        loadFragment(HomeFragment())
    }

    private fun setupViewModel() {
        val userEmail = intent.getStringExtra("USER_EMAIL")
        val userName = intent.getStringExtra("USER_NAME")
        sharedViewModel.setUserData(userEmail, userName)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_search -> SearchFragment()
                R.id.nav_profile -> ProfileFragment()
                else -> HomeFragment()
            }
            loadFragment(fragment)
            true
        }
        binding.bottomNavigation.selectedItemId = R.id.nav_home
    }
}
