package com.example.pecipe_blud

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pecipe_blud.databinding.ActivityMainBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.auth.ktx.auth

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Firebase.auth.signOut()
        // Инициализация Firebase
        auth = Firebase.auth

        // Настройка ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Кнопка входа
        binding.button.setOnClickListener {
            val email = binding.editTextTextEmailAddress.text.toString().trim()
            val password = binding.editTextTextPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Заполните все поля", Toast.LENGTH_SHORT).show()
            } else {
                loginUser(email, password)
            }
        }

        // Кнопка регистрации
        binding.textView1.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }

        // Вход без регистрации
        binding.textView2.setOnClickListener {
            startActivity(Intent(this, ReceptyActivity::class.java))
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Успешный вход без проверки email
                    startActivity(Intent(this, ReceptyActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "Ошибка: ${task.exception?.message ?: "Неверные данные"}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

    override fun onStart() {
        super.onStart()
        // Автоматический вход если пользователь уже авторизован
        //if (auth.currentUser != null) {
            //startActivity(Intent(this, ReceptyActivity::class.java))
            //finish()
        //}
    }
}