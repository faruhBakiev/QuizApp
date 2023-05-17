package com.example.presentation.ui.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.presentation.R
import com.example.presentation.databinding.ItemQuestionsBinding
import com.example.presentation.models.ResultsItemUI
import com.example.presentation.ui.fragments.question.QuestionFragmentDirections

class QuestionsAdapter(private val onItemClick: ((position: Int, answer: Int) -> Unit)? = null
) :
    ListAdapter<ResultsItemUI, QuestionsAdapter.QuestionsViewHolder>(diffUtil) {

    inner class QuestionsViewHolder(private val binding: ItemQuestionsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(item: ResultsItemUI) {
            binding.tvCategory.text = item.category
            binding.tvQuestion.text = item.question
            val answers = mutableListOf<String>()
            item.incorrectAnswers?.let { answers.addAll(it) }
            answers.add(item.correctAnswer)
            answers.shuffle()

            if (answers.size > 2) {
                binding.tvAnswer1.isVisible = true
                binding.tvAnswer4.isVisible = true

                binding.tvAnswer1.text = answers[0]
                binding.tvAnswer2.text = answers[1]
                binding.tvAnswer3.text = answers[2]
                binding.tvAnswer4.text = answers[3]

            } else {
                binding.tvAnswer1.isVisible = false
                binding.tvAnswer4.isVisible = false
                binding.tvAnswer2.text = answers[0]
                binding.tvAnswer3.text = answers[1]
            }

            val count = "${adapterPosition + 1}/${currentList.size}"
            binding.tvProgress.text = count
            binding.progressLinear.max = currentList.size
            binding.progressLinear.progress = adapterPosition + 1

            binding.btnSkip.setOnClickListener {
                onItemClick?.invoke(adapterPosition, 0)
            }

            defaultColor()
            pressingBtn(true)

            binding.tvAnswer1.setOnClickListener {
                checkAnswer(binding.tvAnswer1, item)
                pressingBtn(false)
            }
            binding.tvAnswer2.setOnClickListener {
                checkAnswer(binding.tvAnswer2, item)
                pressingBtn(false)
            }
            binding.tvAnswer3.setOnClickListener {
                checkAnswer(binding.tvAnswer3, item)
                pressingBtn(false)
            }
            binding.tvAnswer4.setOnClickListener {
                checkAnswer(binding.tvAnswer4, item)
                pressingBtn(false)
            }
        }

        private fun defaultColor() {
            binding.tvAnswer1.setBackgroundResource(R.drawable.default_answer)
            binding.tvAnswer2.setBackgroundResource(R.drawable.default_answer)
            binding.tvAnswer3.setBackgroundResource(R.drawable.default_answer)
            binding.tvAnswer4.setBackgroundResource(R.drawable.default_answer)
        }

        private fun checkAnswer(text: TextView, questions: ResultsItemUI) {
            if (text.text == questions.correctAnswer) {
                text.setBackgroundResource(R.drawable.green)
                onItemClick?.invoke(adapterPosition, 1)
            } else {
                text.setBackgroundResource(R.drawable.red)
                onItemClick?.invoke(adapterPosition, 0)
            }
        }

        private fun pressingBtn(boolean: Boolean) {
            binding.tvAnswer1.isEnabled = boolean
            binding.tvAnswer2.isEnabled = boolean
            binding.tvAnswer3.isEnabled = boolean
            binding.tvAnswer4.isEnabled = boolean
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionsViewHolder {
        return QuestionsViewHolder(
            ItemQuestionsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: QuestionsViewHolder, position: Int) {
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