package com.example.userslist.presentation.registerUser

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.example.userslist.R
import com.example.userslist.common.BaseFragment
import com.example.userslist.common.Resource
import com.example.userslist.common.showToast
import com.example.userslist.data.remote.Credential
import com.example.userslist.databinding.FragmentLoginBinding
import com.example.userslist.databinding.FragmentRegisterBinding
import com.example.userslist.presentation.loginUser.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register) {

    private val viewmodel by activityViewModels<RegisterViewModel>()

    override fun FragmentRegisterBinding.initialize() {
        binding?.button?.setOnClickListener {
            if (isValid()) {
                viewmodel.registerUser(
                    Credential(
                        email = editTextEmail.text.toString(),
                        password = editTextPassword.text.toString()
                    )
                ).observe(viewLifecycleOwner){
                    when(it){
                        is Resource.Loading -> {
                            showToast(requireContext(),"Validating User")
                        }
                        is Resource.Success -> {
                            if(it.data != null){
                                showToast(requireContext(),"Register user success - ${it.data}")
                                requireActivity().onBackPressed()
                            }
                        }
                        is Resource.Error -> {
                            showToast(requireContext(),"Register user error - ${it.data}")
                        }
                    }
                }
            }
        }
    }

    private fun isValid(): Boolean {
        if (TextUtils.isEmpty(binding?.editTextEmail?.text.toString())) {
            Toast.makeText(requireActivity(), "Please Enter Email Id", Toast.LENGTH_SHORT).show()
            return false
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(binding?.editTextEmail?.text.toString()).matches()) {
            Toast.makeText(requireActivity(), "Please Enter Valid Email Id", Toast.LENGTH_SHORT)
                .show()
            return false
        }

        if (TextUtils.isEmpty(binding?.editTextPassword?.text.toString())) {
            Toast.makeText(requireActivity(), "Please Enter Password", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}