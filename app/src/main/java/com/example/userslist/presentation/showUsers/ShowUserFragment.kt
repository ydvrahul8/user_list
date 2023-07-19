package com.example.userslist.presentation.showUsers

import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.userslist.R
import com.example.userslist.common.BaseFragment
import com.example.userslist.common.Resource
import com.example.userslist.common.showToast
import com.example.userslist.data.remote.User
import com.example.userslist.databinding.FragmentShowUserBinding
import com.example.userslist.presentation.showUsers.adapter.UserAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShowUserFragment : BaseFragment<FragmentShowUserBinding>(R.layout.fragment_show_user) {

    private val viewmodel by activityViewModels<ShowUsersViewModel>()
    private var adapter:UserAdapter?=null
    override fun FragmentShowUserBinding.initialize() {
        adapter = UserAdapter()
        binding?.recyclerView?.adapter = adapter
        viewmodel.getUser().observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {
                    showToast(requireContext(), "Loading User")
                }

                is Resource.Success -> {
                    if (it.data != null) {
                        showToast(requireContext(), "Show Users success - ${it.data}")
                        adapter?.setData(it.data)
                    }
                }

                is Resource.Error -> {
                    showToast(requireContext(), "Show Users error - ${it.data}")
                }
            }
        }

        binding?.fabAddUser?.setOnClickListener {
            findNavController().navigate(R.id.action_showUserFragment_to_addUserFragment)
        }
    }
}