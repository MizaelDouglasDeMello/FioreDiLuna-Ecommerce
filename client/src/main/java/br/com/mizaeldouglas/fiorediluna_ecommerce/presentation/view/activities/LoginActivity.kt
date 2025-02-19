package br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.view.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.mizaeldouglas.fiorediluna_ecommerce.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Verifica se o usuário já está autenticado
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            // Se o usuário já está logado, vá direto para a HomeActivity
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("USER_EMAIL", currentUser.email)
            intent.putExtra("USER_NAME", currentUser.displayName)
            startActivity(intent)
            finish()
        } else {
            // Se o usuário não está logado, exiba a tela de login
            setContentView(binding.root)
            initializer()
        }
    }

    private fun initializer() {
        binding.btnLogin.setOnClickListener {
            val email = binding.textInputEditEmail.text.toString()
            val password = binding.textInputEditPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtLoginRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val intent = Intent(this, HomeActivity::class.java)
                    intent.putExtra("USER_EMAIL", user?.email)
                    intent.putExtra("USER_NAME", user?.displayName ?: "Usuário")
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Erro: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }

}
