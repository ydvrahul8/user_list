package com.example.userslist.presentation.addUser

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.userslist.R
import com.example.userslist.common.BaseFragment
import com.example.userslist.common.Resource
import com.example.userslist.common.showToast
import com.example.userslist.data.remote.Credential
import com.example.userslist.data.remote.User
import com.example.userslist.databinding.FragmentAddUserBinding
import com.example.userslist.databinding.FragmentLoginBinding
import com.example.userslist.presentation.loginUser.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddUserFragment : BaseFragment<FragmentAddUserBinding>(R.layout.fragment_add_user) {

    private val viewmodel by activityViewModels<AddUserViewModel>()
    override fun FragmentAddUserBinding.initialize() {
        binding?.button?.setOnClickListener {
            if (isValid()) {
                viewmodel.addUser(
                    User(
                        job = editTextJob.text.toString(),
                        name = editTextName.text.toString()
                    )
                ).observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Loading -> {
                            showToast(requireContext(), "Adding User")
                        }

                        is Resource.Success -> {
                            if (it.data != null) {
                                showToast(requireContext(), "User Added success - ${it.data}")
                                requireActivity().onBackPressed()
                            }
                        }

                        is Resource.Error -> {
                            showToast(requireContext(), "User Added error - ${it.data}")
                        }
                    }
                }
            }
        }
    }

    private fun isValid(): Boolean {
        if (TextUtils.isEmpty(binding?.editTextJob?.text.toString())) {
            Toast.makeText(requireActivity(), "Please Enter job", Toast.LENGTH_SHORT).show()
            return false
        }

        if (TextUtils.isEmpty(binding?.editTextName?.text.toString())) {
            Toast.makeText(requireActivity(), "Please Enter Name", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}