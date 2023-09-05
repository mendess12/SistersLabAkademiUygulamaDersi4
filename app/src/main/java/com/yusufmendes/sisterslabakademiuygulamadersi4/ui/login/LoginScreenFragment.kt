package com.yusufmendes.sisterslabakademiuygulamadersi4.ui.login

import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yusufmendes.sisterslabakademiuygulamadersi4.R
import com.yusufmendes.sisterslabakademiuygulamadersi4.databinding.FragmentLoginScreenBinding

class LoginScreenFragment : Fragment(R.layout.fragment_login_screen) {

    private lateinit var binding: FragmentLoginScreenBinding
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginScreenBinding.bind(view)

        auth = Firebase.auth
        if (auth.currentUser != null) {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_loginScreenFragment_to_homeFragment)
        }

        binding.loginScreenLoginButton.setOnClickListener {
            val email = binding.loginScreenEmailEt.text.toString()
            val password = binding.loginScreenPasswordEt.text.toString()

            if (email.isEmpty()) {
                binding.loginScreenEmailEt.error = "Email required"
                binding.loginScreenEmailEt.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.loginScreenEmailEt.error = "Valid email required"
                binding.loginScreenEmailEt.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty() || password.length < 6) {
                binding.loginScreenPasswordEt.error = "6 char password required"
                binding.loginScreenPasswordEt.requestFocus()
                return@setOnClickListener
            }
            checkLogin(email, password)
        }

        binding.loginScreenRegisterTv.setOnClickListener {
            Navigation.findNavController(requireView())
                .navigate(R.id.action_loginScreenFragment_to_registerScreenFragment)
        }
    }

    private fun checkLogin(email: String, password: String) {
        auth = Firebase.auth
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_loginScreenFragment_to_homeFragment)
            } else {
                Snackbar.make(requireView(), "Authentication failed.", Snackbar.LENGTH_SHORT).show()
            }
        }
    }
}