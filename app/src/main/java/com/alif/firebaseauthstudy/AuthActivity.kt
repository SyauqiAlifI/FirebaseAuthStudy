package com.alif.firebaseauthstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alif.firebaseauthstudy.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private var _binding : ActivityAuthBinding? = null
    private val binding get() = _binding as ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignInAuth.setOnClickListener {
            startActivity(Intent(this, SignInActivity::class.java))
        }
        binding.btnSignUpAuth.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }
}