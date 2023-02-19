package com.jdr.pruebaceiba.ui.main.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jdr.pruebaceiba.R
import com.jdr.pruebaceiba.databinding.ItemUserBinding
import com.jdr.pruebaceiba.model.UserModel

class UserAdapter : ListAdapter<UserModel, UserAdapter.UserViewHolder>(diffCallBack) {

    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<UserModel>() {
            override fun areItemsTheSame(
                oldItem: UserModel,
                newItem: UserModel
            ): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: UserModel,
                newItem: UserModel
            ): Boolean =
                oldItem.id == newItem.id
        }
    }

    var onClickListener: ((UserModel) -> Unit)? = null

    inner class UserViewHolder(private val binding: ItemUserBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun render(data: UserModel) {
            with(binding) {
                tvNamePerson.text = data.name
                tvEmail.text = data.email
                tvPhone.text = data.phone

                tvSeePublication.setOnClickListener {
                    onClickListener?.invoke(data)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
        )
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.render(getItem(position))
    }

}