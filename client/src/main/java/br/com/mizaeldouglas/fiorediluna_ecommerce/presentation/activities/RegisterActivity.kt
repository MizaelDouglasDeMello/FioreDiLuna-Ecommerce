package br.com.mizaeldouglas.fiorediluna_ecommerce.presentation.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.mizaeldouglas.fiorediluna_ecommerce.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private val binding by lazy { ActivityRegisterBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        initializer()
    }

    private fun initializer() {
        auth = FirebaseAuth.getInstance()
        binding.btnRegister.setOnClickListener {
            val email = binding.textInputEditEmail.text.toString()
            val password = binding.textInputEditPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                registerUser(email, password)
            } else {
                Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun registerUser(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Registro realizado com sucesso!", Toast.LENGTH_SHORT)
                        .show()
                    // Navegar para outra Activity, se necessário
                } else {
                    Toast.makeText(this, "Erro: ${task.exception?.message}", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }
}