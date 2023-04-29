package com.example.presentation.ui.fragments.question

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentQuestionBinding
import com.example.presentation.state.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class QuestionFragment : BaseFragment<FragmentQuestionBinding, QuestionViewModel>(R.layout.fragment_question) {

    override val binding by viewBinding(FragmentQuestionBinding::bind)
    override val viewModel: QuestionViewModel by viewModels()
    private val args by navArgs<QuestionFragmentArgs>()

    override fun setupRequests() {
        viewModel.fetchCategory(args.category, args.difficulty)
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
                        }
                        is UIState.Success -> {
                            Log.e("all", "success")
                            Toast.makeText(
                                requireContext(), it.data.toString(), Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                }
            }
        }
    }
}