package com.yusufmendes.sisterslabakademiuygulamadersi4.ui.register

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
import com.yusufmendes.sisterslabakademiuygulamadersi4.databinding.FragmentRegisterScreenBinding

class RegisterScreenFragment : Fragment(R.layout.fragment_register_screen) {

    private lateinit var binding: FragmentRegisterScreenBinding
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterScreenBinding.bind(view)

        binding.registerScreenRegisterButton.setOnClickListener {
            val email = binding.registerScreenEmailEt.text.toString()
            val password = binding.registerScreenPasswordEt.text.toString()

            if (email.isEmpty()) {
                binding.registerScreenEmailEt.error = "Email required"
                binding.registerScreenEmailEt.requestFocus()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                binding.registerScreenEmailEt.error = "Valid email required"
                binding.registerScreenEmailEt.requestFocus()
                return@setOnClickListener
            }
            if (password.isEmpty() || password.length < 6) {
                binding.registerScreenPasswordEt.error = "6 char password required"
                binding.registerScreenPasswordEt.requestFocus()
                return@setOnClickListener
            }
            register(email, password)
        }

        binding.registerScreenLoginTv.setOnClickListener {
            Navigation.findNavController(it)
                .navigate(R.id.action_registerScreenFragment_to_loginScreenFragment2)
        }
    }

    private fun register(email: String, password: String) {
        auth = Firebase.auth
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Navigation.findNavController(requireView())
                    .navigate(R.id.action_registerScreenFragment_to_loginScreenFragment2)
            } else {
                Snackbar.make(requireView(), "Authentication failed.", Snackbar.LENGTH_SHORT)
                    .show()
            }
        }
    }
}