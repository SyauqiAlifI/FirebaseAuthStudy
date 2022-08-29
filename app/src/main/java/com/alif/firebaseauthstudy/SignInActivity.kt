package com.alif.firebaseauthstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alif.firebaseauthstudy.databinding.ActivitySignInBinding

class SignInActivity : AppCompatActivity() {

    private var _bind: ActivitySignInBinding? = null
    private val binding get() = _bind as ActivitySignInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bind = ActivitySignInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initActionBar()

        binding.apply {
            btnSignIn.setOnClickListener {
                startActivity(Intent(this@SignInActivity, MainActivity::class.java))
            }
            btnForgotPass.setOnClickListener {
                startActivity(Intent(this@SignInActivity, ForgotPasswordActivity::class.java))
            }
            tbSignIn.setNavigationOnClickListener {
                finish()
            }
        }
    }

    private fun initActionBar() {
        setSupportActionBar(binding.tbSignIn)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }
}