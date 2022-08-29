package com.alif.firebaseauthstudy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alif.firebaseauthstudy.databinding.ActivityForgotPasswordBinding

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
                Toast.makeText(this@ForgotPasswordActivity, "Send Email", Toast.LENGTH_SHORT).show()
            }
            tbForgotPass.setNavigationOnClickListener {
                finish()
            }
        }
    }
    private fun initActionBar() {
        setSupportActionBar(binding.tbForgotPass)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }
}