package br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import br.com.mizaeldouglas.fiorediluna_ecommerce.R
import br.com.mizaeldouglas.fiorediluna_ecommerce.databinding.ActivityHomeBinding
import br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.fragments.HomeFragment
import br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.fragments.ProfileFragment
import br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.fragments.SearchFragment

class HomeActivity : AppCompatActivity() {

    private val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializer()

    }

    private fun initializer() {
        setupBottomNavigation()
        loadFragment(HomeFragment())
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
        // Seleciona o item "Home" como ativo
        binding.bottomNavigation.selectedItemId = R.id.nav_home
    }
}