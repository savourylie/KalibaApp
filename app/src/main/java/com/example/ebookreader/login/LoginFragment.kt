package com.example.ebookreader.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.ebookreader.R
import com.example.ebookreader.databinding.FragmentLoginBinding
import com.example.ebookreader.utils.TypeFaceStyler

class LoginFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding: FragmentLoginBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login, container, false)

        // Style UI
        val loginDescription = binding.loginDescription
        loginDescription.text =
            TypeFaceStyler(requireContext(), loginDescription)
            .styleText( "Kaliba", R.font.nunito_black)
            .styleText( "Reader", R.font.nunito_extralight_italic)
            .spannable

        binding.invalidateAll()

        binding.loginButton.setOnClickListener {
            findNavController()
                .navigate(R.id.action_loginFragment_to_fileExplorerFragment)
        }

        return binding.root
    }
}