package com.yusufmendes.sisterslabakademiuygulamadersi4.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.Navigation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.yusufmendes.sisterslabakademiuygulamadersi4.R
import com.yusufmendes.sisterslabakademiuygulamadersi4.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var auth: FirebaseAuth

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        auth = Firebase.auth

        binding.homeScreenSignOutTv.setOnClickListener {
            auth.signOut()
            Navigation.findNavController(it)
                .navigate(R.id.action_homeFragment_to_loginScreenFragment)
        }
    }
}