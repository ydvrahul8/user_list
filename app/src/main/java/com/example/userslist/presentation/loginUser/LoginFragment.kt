package com.example.userslist.presentation.loginUser

import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.userslist.R
import com.example.userslist.common.BaseFragment
import com.example.userslist.common.Resource
import com.example.userslist.common.showToast
import com.example.userslist.data.remote.Credential
import com.example.userslist.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {

    private val viewmodel by activityViewModels<LoginViewModel>()
    override fun FragmentLoginBinding.initialize() {
        binding?.textViewRegister?.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
        binding?.buttonLogin?.setOnClickListener {
            if (isValid()) {
                viewmodel.loginUser(
                    Credential(
                        email = editTextEmail.text.toString(),
                        password = editTextPassword.text.toString()
                    )
                ).observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Loading -> {
                            showToast(requireContext(), "Validating User")
                        }

                        is Resource.Success -> {
                            if (it.data != null) {
                                showToast(requireContext(), "Login user success - ${it.data}")
                                findNavController().navigate(R.id.action_loginFragment_to_showUserFragment)
                            }
                        }

                        is Resource.Error -> {
                            showToast(requireContext(), "Login user error - ${it.data}")
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