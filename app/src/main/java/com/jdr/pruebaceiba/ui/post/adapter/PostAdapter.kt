package com.jdr.pruebaceiba.ui.post.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jdr.pruebaceiba.R
import com.jdr.pruebaceiba.databinding.ItemPostBinding
import com.jdr.pruebaceiba.model.PostModel

class PostAdapter : ListAdapter<PostModel, PostAdapter.PostViewHolder>(diffCallBack) {


    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<PostModel>() {
            override fun areItemsTheSame(
                oldItem: PostModel,
                newItem: PostModel
            ): Boolean =
                oldItem == newItem

            override fun areContentsTheSame(
                oldItem: PostModel,
                newItem: PostModel
            ): Boolean =
                oldItem.id == newItem.id
        }
    }

    inner class PostViewHolder(private val binding: ItemPostBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun render(data: PostModel) {
            with(binding) {
                tvTitle.text = data.title
                tvDescription.text = data.body
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ItemPostBinding.bind(
            LayoutInflater.from(parent.context).inflate(R.layout.item_post, parent, false)
        )
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.render(getItem(position))
    }
}