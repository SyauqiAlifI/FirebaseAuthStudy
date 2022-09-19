package com.alif.firebaseauthstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.alif.firebaseauthstudy.databinding.ActivityForgotPasswordBinding
import com.google.firebase.auth.FirebaseAuth

class ForgotPasswordActivity : AppCompatActivity() {
    private var _binding: ActivityForgotPasswordBinding? = null
    private val binding get() = _binding as ActivityForgotPasswordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityForgotPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        binding.apply {
            btnSendEmail.setOnClickListener {
                val email = binding.etEmailForgotPass.text.toString().trim()
                if (email.isEmpty()) {
                    binding.etEmailForgotPass.error = "Please enter your email"
                    binding.etEmailForgotPass.requestFocus()
                    return@setOnClickListener
                } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.etEmailForgotPass.error = "Please enter a valid email"
                    binding.etEmailForgotPass.requestFocus()
                    return@setOnClickListener
                } else {
                    forgotPass(email)
                }
            }
            tbForgotPass.setNavigationOnClickListener {
                finish()
            }
        }
    }

    private fun forgotPass(email: String) {
        FirebaseAuth.getInstance().sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Your request has been sent to your email", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, SignInActivity::class.java))
                    finishAffinity()
                } else {
                    Toast.makeText(this, "Failed to reset password", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            }
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbForgotPass)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }
}