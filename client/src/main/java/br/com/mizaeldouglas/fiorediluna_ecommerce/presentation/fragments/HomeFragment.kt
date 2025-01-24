package br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import br.com.mizaeldouglas.fiorediluna_ecommerce.R

class HomeFragment : Fragment() {

    companion object {
        private const val ARG_USER_EMAIL = "user_email"
        private const val ARG_USER_NAME = "user_name"

        fun newInstance(email: String?, name: String?): HomeFragment {
            val fragment = HomeFragment()
            val args = Bundle()
            args.putString(ARG_USER_EMAIL, email)
            args.putString(ARG_USER_NAME, name)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Recupera os argumentos (se necessário)
        val userEmail = arguments?.getString(ARG_USER_EMAIL)
        val userName = arguments?.getString(ARG_USER_NAME)

        // Aqui você pode usar os dados do usuário se precisar
    }
}
