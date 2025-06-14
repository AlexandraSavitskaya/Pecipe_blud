package com.example.pecipe_blud

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.pecipe_blud.ReceptyActivity
import com.example.pecipe_blud.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    //private lateinit var editTextLogin: EditText
    //private lateinit var editTextPassword: EditText
    //private lateinit var buttonLogin: Button
    //private lateinit var textViewMessage: TextView

    //private val correctLogin = "Savitskaya"
    //private val correctPassword = "qwerty"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //editTextLogin = findViewById(R.id.editTextTextEmailAddress)
        //editTextPassword = findViewById(R.id.editTextTextPassword)
        //buttonLogin = findViewById(R.id.button)
        //textViewMessage = findViewById(R.id.textView)

        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)

        mainBinding.button.setOnClickListener {

            //val enteredLogin = mainBinding.editTextTextEmailAddress.text.toString()
            //val enteredPassword = mainBinding.editTextTextPassword.text.toString()
            //Если почта и пароль верные
            //if ("@+id/editTextTextEmailAddress" == correctLogin && "@+id/editTextTextPassword" == correctPassword) {
            // Тогда осуществляется переход на ReceptyActivity
            val intent = Intent(this,ReceptyActivity::class.java)
            startActivity(intent)

            // }
        }
        mainBinding.textView2.setOnClickListener {
            val intent = Intent(this,
                ReceptyActivity::class.java
            )
            startActivity(intent)
        }
    }

}