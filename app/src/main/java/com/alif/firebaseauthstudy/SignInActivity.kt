package com.alif.firebaseauthstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.alif.firebaseauthstudy.databinding.ActivitySignInBinding
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    private var _bind: ActivitySignInBinding? = null
    private val binding get() = _bind as ActivitySignInBinding

    private lateinit var fAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bind = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()
        initFirebaseAuth()

        binding.apply {
            btnSignIn.setOnClickListener {
                val email = etEmailSignIn.text.toString().trim()
                val pass = etPasswordSignIn.text.toString().trim()

                if (checkValidation(email, pass)) {
                    loginToServer(email, pass)
                }
            }
            btnForgotPass.setOnClickListener {
                startActivity(Intent(this@SignInActivity, ForgotPasswordActivity::class.java))
            }
            tbSignIn.setNavigationOnClickListener {
                finish()
            }
        }
    }

    private fun loginToServer(email: String, pass: String) {
        val credential = EmailAuthProvider.getCredential(email, pass)
        firebaseAuth(credential)
    }

    private fun firebaseAuth(credential: AuthCredential) {
        fAuth.signInWithCredential(credential)
            .addOnCompleteListener { task ->
                CustomDialog.hideLoading()
                if (task.isSuccessful) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finishAffinity()
                } else {
                    Toast.makeText(this, "Sign-In Failed", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                CustomDialog.hideLoading()
                Toast.makeText(this, exception.message, Toast.LENGTH_SHORT).show()
            }
    }

    private fun checkValidation(email: String, pass: String): Boolean {
        if (email.isEmpty()) {
            binding.etEmailSignIn.error = "Please enter your email"
            binding.etEmailSignIn.requestFocus()
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            binding.etEmailSignIn.error = "Please use a valid email"
            binding.etEmailSignIn.requestFocus()
        } else if (pass.isEmpty()) {
            binding.etPasswordSignIn.error = "Please enter your password"
            binding.etPasswordSignIn.requestFocus()
        } else {
            return true
        }
        CustomDialog.hideLoading()
        return false
    }

    private fun initFirebaseAuth() {
        fAuth = FirebaseAuth.getInstance()
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbSignIn)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }
}