package br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.view.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import br.com.mizaeldouglas.fiorediluna_ecommerce.R
import br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.view.activities.LoginActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class ProfileFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private var currentUser: FirebaseUser? = null

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
        currentUser = auth.currentUser

        // Recupera os elementos da UI
        val tvEmail: TextView = view.findViewById(R.id.tv_user_email)
        val tvName: TextView = view.findViewById(R.id.tv_user_name)
        val btnLogout: Button = view.findViewById(R.id.btn_logout)

        // Exibe o e-mail e nome do usuário
        tvEmail.text = currentUser?.email ?: "Email não disponível"
        tvName.text = currentUser?.displayName ?: "Nome não disponível"

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
