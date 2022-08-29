package com.alif.firebaseauthstudy

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.alif.firebaseauthstudy.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private var _binding: ActivitySignUpBinding? = null
    private val binding get() = _binding as ActivitySignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        binding.apply {
            btnSignUp.setOnClickListener {
                startActivity(Intent(this@SignUpActivity, MainActivity::class.java))
            }
            tbSignUp.setNavigationOnClickListener {
                finish()
            }
        }
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbSignUp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }
}