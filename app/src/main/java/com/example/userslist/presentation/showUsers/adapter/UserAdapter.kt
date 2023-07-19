package com.example.userslist.presentation.showUsers.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.userslist.R
import com.example.userslist.databinding.ItemUserBinding
import com.example.userslist.domain.model.UserData

class UserAdapter :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    private var list = ArrayList<UserData>()

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindTo(data: UserData, position: Int) = with(binding) {
            userData = data
            Glide.with(imageView.rootView).load(data.avatar)
                .timeout(6000).placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background).centerInside()
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindTo(list[position], position)
    }

    override fun getItemCount(): Int = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<UserData>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

}
