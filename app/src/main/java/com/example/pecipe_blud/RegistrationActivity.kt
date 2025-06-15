package com.example.pecipe_blud

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.example.pecipe_blud.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity() {

    // Инициализация Firebase Auth
    private lateinit var auth: FirebaseAuth

    // ViewBinding
    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // Инициализация ViewBinding
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получаем экземпляр FirebaseAuth
        auth = Firebase.auth

        // Обработка системных окон
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Обработка нажатия кнопки регистрации
        binding.button2.setOnClickListener {
            registerUser()
        }
    }

    private fun registerUser() {
        val email = binding.editTextTextEmailAddress2.text.toString().trim()
        val password = binding.editTextTextPassword2.text.toString().trim()
        val confirmPassword = binding.editTextTextPassword3.text.toString().trim()
        val name = binding.editTextText.text.toString().trim()

        // Валидация полей
        if (email.isEmpty()) {
            binding.editTextTextEmailAddress2.error = "Email обязателен"
            binding.editTextTextEmailAddress2.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.editTextTextEmailAddress2.error = "Введите корректный email"
            binding.editTextTextEmailAddress2.requestFocus()
            return
        }

        if (password.isEmpty()) {
            binding.editTextTextPassword2.error = "Пароль обязателен"
            binding.editTextTextPassword2.requestFocus()
            return
        }

        if (password.length < 6) {
            binding.editTextTextPassword2.error = "Пароль должен быть не менее 6 символов"
            binding.editTextTextPassword2.requestFocus()
            return
        }

        if (password != confirmPassword) {
            binding.editTextTextPassword3.error = "Пароли не совпадают"
            binding.editTextTextPassword3.requestFocus()
            return
        }

        // Регистрация пользователя
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Успешная регистрация
                    val user = auth.currentUser
                    Toast.makeText(
                        this, "Регистрация успешна!",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(user)
                } else {
                    // Ошибка регистрации
                    Toast.makeText(
                        this, "Ошибка: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: Any?) {
        if (user != null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        // Проверяем, вошел ли пользователь
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }
}