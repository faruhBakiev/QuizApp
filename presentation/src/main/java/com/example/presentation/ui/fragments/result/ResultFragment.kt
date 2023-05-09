package com.example.presentation.ui.fragments.result

import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentResultBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResultFragment :
    BaseFragment<FragmentResultBinding,ResultViewModel>(R.layout.fragment_result) {

    override val binding by viewBinding(FragmentResultBinding::bind)
    override val viewModel: ResultViewModel by viewModels()
}
