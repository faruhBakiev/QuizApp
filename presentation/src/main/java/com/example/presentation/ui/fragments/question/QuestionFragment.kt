package com.example.presentation.ui.fragments.question

import android.util.Log
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentQuestionBinding
import com.example.presentation.ui.adapters.QuestionsAdapter
import com.example.presentation.ui.state.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuestionFragment :
    BaseFragment<FragmentQuestionBinding, QuestionViewModel>(R.layout.fragment_question) {

    override val binding by viewBinding(FragmentQuestionBinding::bind)
    override val viewModel: QuestionViewModel by viewModels()
    private val args by navArgs<QuestionFragmentArgs>()
    private val questionsAdapter = QuestionsAdapter(this::onItemClick)

    override fun initialize() {
        setupRecycler()
    }

    override fun setupListeners() {
        binding.btnBack.setOnClickListener {
            findNavController().navigate(
                QuestionFragmentDirections.actionQuestionFragmentToHomeFragment()
            )
        }
    }

    private fun setupRecycler() = with(binding.rvQuiz) {
        layoutManager = object : LinearLayoutManager(requireContext(), VERTICAL, false) {
            override fun canScrollVertically() = false
        }
        adapter = questionsAdapter
        setItemViewCacheSize(1)
    }

    override fun setupRequests() {
        viewModel.fetchCategory(args.category, args.difficulty, args.amount)
    }

    override fun setupSubscribes() {
        subscribeToQuestions()
    }

    private fun subscribeToQuestions() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.categoryState.collect {
                    when (it) {
                        is UIState.Error -> {
                            Log.e("all", it.error)
                            Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
                        }
                        is UIState.Loading -> {
                            Log.e("all", "loading...")
                            binding.progressBar.isVisible = true
                        }
                        is UIState.Success -> {
                            Log.e("all", "success")
                            it.data.let { data ->
                                questionsAdapter.submitList(data)
                                binding.progressBar.isVisible = false
                            }
                        }
                    }
                }
            }
        }
    }

    private fun onItemClick(position: Int, answer: Int) {
        viewLifecycleOwner.lifecycleScope.launch {
            delay(900)
            binding.rvQuiz.scrollToPosition(position + 1)
        }
    }
}