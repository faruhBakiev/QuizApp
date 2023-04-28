package com.example.presentation.ui.fragments.question

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentQuestionBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionFragment : BaseFragment<FragmentQuestionBinding, QuestionViewModel>(R.layout.fragment_question) {

    override val binding by viewBinding(FragmentQuestionBinding::bind)
    override val viewModel: QuestionViewModel by viewModels()
    private val args by navArgs<QuestionFragmentArgs>()

    override fun setupRequests() {

    }
}