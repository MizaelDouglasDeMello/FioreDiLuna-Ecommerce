package br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import br.com.mizaeldouglas.fiorediluna_ecommerce.R
import br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.activities.LoginActivity
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth

    companion object {
        private const val ARG_USER_EMAIL = "user_email"
        private const val ARG_USER_NAME = "user_name"

        fun newInstance(email: String?, name: String?): ProfileFragment {
            val fragment = ProfileFragment()
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
    ): View {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Inicializa o FirebaseAuth
        auth = FirebaseAuth.getInstance()

        // Recupera os argumentos
        val userEmail = arguments?.getString(ARG_USER_EMAIL)
        val userName = arguments?.getString(ARG_USER_NAME)

        // Exibe os dados no layout
        val tvEmail: TextView = view.findViewById(R.id.tv_user_email)
        val tvName: TextView = view.findViewById(R.id.tv_user_name)
        val btnLogout: Button = view.findViewById(R.id.btn_logout)

        tvEmail.text = userEmail ?: "Email não disponível"
        tvName.text = userName ?: "Nome não disponível"

        // Configura o botão de logout
        btnLogout.setOnClickListener {
            auth.signOut() // Faz logout do Firebase

            // Redireciona para a LoginActivity
            val intent = Intent(requireContext(), LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            requireActivity().finish()
        }
    }
}
