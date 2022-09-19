package com.alif.firebaseauthstudy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alif.firebaseauthstudy.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding as ActivityMainBinding

    private lateinit var fAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFirebaseAuth()
        getData()

        binding.btnLogout.setOnClickListener {
            fAuth.signOut()
            startActivity(Intent(this, AuthActivity::class.java))
            finish()
        }
    }

    private fun getData() {
        val user = fAuth.currentUser
        if (user != null) {
            binding.tvEmail.text = user.email
        }
    }

    private fun initFirebaseAuth() {
        fAuth = FirebaseAuth.getInstance()
    }
}