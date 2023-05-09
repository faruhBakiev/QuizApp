package com.example.presentation.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.databinding.ItemQuestionsTrueFalseBinding
import com.example.presentation.models.ResultsItemUI

class TrueFalseAdapter() :
    ListAdapter<ResultsItemUI, TrueFalseAdapter.AdapterViewHolder>(diffUtil) {

    val onItemClick: ((position: Int, answer: Int) -> Unit)? = null

    inner class AdapterViewHolder(private val binding: ItemQuestionsTrueFalseBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: ResultsItemUI) {
            binding.tvCategory.text = item.category
            binding.tvQuestion.text = item.question

            val answers = mutableListOf<String>()
            item.incorrectAnswers?.let { answers.addAll(it) }
            answers.add(item.correctAnswer)
            answers.shuffle()

            binding.tvTrue.text = answers[0]
            binding.tvFalse.text = answers[1]

            val count = "${adapterPosition + 1}/${currentList.size}"
            binding.tvProgress.text = count
            binding.progressLinear.max = currentList.size
            binding.progressLinear.progress = adapterPosition + 1

            binding.btnSkip.setOnClickListener {
                onItemClick?.invoke(adapterPosition,0)
            }

            defaultColor()
            pressingBtn(true)

            binding.tvTrue.setOnClickListener {
                checkAnswer(binding.tvTrue,item)
//                pressingBtn(false)
            }

            binding.tvFalse.setOnClickListener {
                checkAnswer(binding.tvFalse,item)
//                pressingBtn(false)
            }
        }

        private fun checkAnswer(text: TextView, questions: ResultsItemUI) {
            if (text.text == questions.correctAnswer) {
                text.setBackgroundResource(R.drawable.green)
                onItemClick?.invoke(adapterPosition, 0)
            } else {
                text.setBackgroundResource(R.drawable.red)
                onItemClick?.invoke(adapterPosition, 0)
            }
        }

        private fun defaultColor() {
            binding.tvFalse.setBackgroundResource(R.drawable.default_answer)
            binding.tvTrue.setBackgroundResource(R.drawable.default_answer)
        }

        private fun pressingBtn(boolean: Boolean) {
            binding.tvFalse.isEnabled = boolean
            binding.tvTrue.isEnabled = boolean
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        return AdapterViewHolder(
            ItemQuestionsTrueFalseBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        getItem(position).let {
            holder.onBind(it)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<ResultsItemUI>() {
            override fun areItemsTheSame(oldItem: ResultsItemUI, newItem: ResultsItemUI): Boolean {
                return oldItem.question == newItem.question
            }

            override fun areContentsTheSame(
                oldItem: ResultsItemUI, newItem: ResultsItemUI): Boolean {
                return oldItem == newItem
            }
        }
    }
}