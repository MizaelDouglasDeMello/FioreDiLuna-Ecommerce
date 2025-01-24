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

        // Recupera os dados do Intent
        val userEmail = intent.getStringExtra("USER_EMAIL")
        val userName = intent.getStringExtra("USER_NAME")

        // Passa o Fragment inicial com os dados
        val homeFragment = HomeFragment.newInstance(userEmail, userName)
        loadFragment(homeFragment)
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }



    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val fragment: Fragment = when (item.itemId) {
                R.id.nav_home -> HomeFragment.newInstance(
                    intent.getStringExtra("USER_EMAIL"),
                    intent.getStringExtra("USER_NAME")
                )
                R.id.nav_search -> SearchFragment()
                R.id.nav_profile -> ProfileFragment.newInstance(
                    intent.getStringExtra("USER_EMAIL"),
                    intent.getStringExtra("USER_NAME")
                )
                else -> HomeFragment()
            }
            loadFragment(fragment)
            true
        }
        binding.bottomNavigation.selectedItemId = R.id.nav_home
    }

}
