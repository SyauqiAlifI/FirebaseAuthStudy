package com.alif.firebaseauthstudy

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.alif.firebaseauthstudy.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase

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
                val email = etEmailSignUp.text.toString().trim()
                val pass = etPasswordSignUp.text.toString().trim()
                val confirmPass = etConfirmPasswordSignUp.text.toString().trim()

                CustomDialog.showLoading(this@SignUpActivity)
                if (checkValidation(email, pass, confirmPass)) {
                    registerToServer(email, pass)
                }
            }
            tbSignUp.setNavigationOnClickListener {
                finish()
            }
        }
    }

    private fun registerToServer(email: String, pass: String) {
        FirebaseAuth.getInstance()
            .createUserWithEmailAndPassword(email, pass)
            .addOnCompleteListener {
                CustomDialog.hideLoading()
                if (it.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                }
            }
            .addOnFailureListener {
                CustomDialog.hideLoading()
                Toast.makeText(this, "Authentication Failed", Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkValidation(email: String, pass: String, confirmPass: String): Boolean {
        if (email.isEmpty()) {
            binding.etEmailSignUp.error = "Please enter your email"
            binding.etEmailSignUp.requestFocus()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmailSignUp.error = "Please use a valid email"
            binding.etEmailSignUp.requestFocus()
        } else if (pass.isEmpty()) {
            binding.etPasswordSignUp.error = "Please enter your password"
            binding.etPasswordSignUp.requestFocus()
        } else if (confirmPass.isEmpty()) {
            binding.etConfirmPasswordSignUp.error = "Please confirm your password"
            binding.etConfirmPasswordSignUp.requestFocus()
        } else if (pass != confirmPass) {
            binding.etPasswordSignUp.error = "Your Password didn't match"
            binding.etConfirmPasswordSignUp.error = "Your confirm password didn't match"

            binding.etPasswordSignUp.requestFocus()
            binding.etConfirmPasswordSignUp.requestFocus()
        } else {
            binding.etPasswordSignUp.error = null
            binding.etConfirmPasswordSignUp.error = null
            return true
        }
        CustomDialog.hideLoading()
        return false
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbSignUp)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }
}