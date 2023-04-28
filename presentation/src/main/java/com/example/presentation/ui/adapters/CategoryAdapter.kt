package com.example.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.presentation.databinding.ItemQuestionsBinding
import com.example.presentation.models.ResultsItemUI

class CategoryAdapter : ListAdapter<ResultsItemUI, CategoryAdapter.CategoryViewHolder>(diffUtil) {


    inner class CategoryViewHolder(private val binding: ItemQuestionsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: ResultsItemUI) {
            binding.tvAnswer1.text = item.correctAnswer
            binding.tvQuestion.text = item.question
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ItemQuestionsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position).let {
            holder.onBind(it)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResultsItemUI>() {
            override fun areItemsTheSame(oldItem: ResultsItemUI, newItem: ResultsItemUI): Boolean {
                return oldItem.category == newItem.difficulty
            }

            override fun areContentsTheSame(oldItem: ResultsItemUI, newItem: ResultsItemUI): Boolean {
                return oldItem == newItem

            }
        }
    }
}