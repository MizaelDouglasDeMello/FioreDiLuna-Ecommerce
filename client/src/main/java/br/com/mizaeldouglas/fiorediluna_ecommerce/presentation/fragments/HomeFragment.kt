package br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import br.com.mizaeldouglas.fiorediluna_ecommerce.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        private const val ARG_USER_EMAIL = "user_email"
        private const val ARG_USER_NAME = "user_name"

        fun newInstance(userEmail: String?, userName: String?): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle().apply {
                putString(ARG_USER_EMAIL, userEmail)
                putString(ARG_USER_NAME, userName)
            }
            fragment.arguments = args
            return fragment
        }
    }

    private var userEmail: String? = null
    private var userName: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            userEmail = it.getString(ARG_USER_EMAIL)
            userName = it.getString(ARG_USER_NAME)
        }
    }
}
