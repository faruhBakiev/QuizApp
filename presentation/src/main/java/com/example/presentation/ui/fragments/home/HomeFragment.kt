package com.example.presentation.ui.fragments.home

import android.util.Log
import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentHomeBinding
import com.example.presentation.state.UIState
import com.example.presentation.ui.adapters.SpinnerOnItemSelectedListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    R.layout.fragment_home
) {

    override val binding by viewBinding(FragmentHomeBinding::bind)
    override val viewModel: HomeViewModel by viewModels()
    private var selectedCategory: Int = 0
    private var selectedDifficulty: Int = 0
    private val category = arrayOf(
        "Any Category",
        "General Knowledge",
        "Entertainment: Books",
        "Entertainment: Film",
        "Entertainment: Music",
        "Entertainment: Musicals & Theatres",
        "Entertainment: Television",
        "Entertainment: Video Games",
        "Entertainment: Board Games",
        "Science & Nature",
        "Science: Computers",
        "Science: Mathematics",
        "Mythology",
        "Sports",
        "Geography",
        "History",
        "Politics",
        "Art",
        "Celebrities",
        "Animals"
    )

    private val difficulty = arrayOf(
        "Any Difficulty", "Easy", "Medium", "Hard"
    )

    override fun initialize() {
        spinnerFunctionCategory()
        seekbarFunction()
        spinnerFunctionDifficulty()

    }

    private fun spinnerFunctionDifficulty() {
        binding.spinnerDifficulty.setSelection(0)
        ArrayAdapter(
            requireContext(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item, difficulty
        ).also { adapter ->
            adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
            binding.spinnerDifficulty.adapter = adapter
        }
        binding.spinnerDifficulty.onItemSelectedListener =
            SpinnerOnItemSelectedListener(difficulty) { difficulty ->
                difficulty?.let {
                    selectedDifficulty = it
                }
            }
    }

    private fun spinnerFunctionCategory() {
        binding.spinnerCategory.setSelection(0)
        ArrayAdapter(
            requireContext(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item, category
        ).also { adapter ->
            adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
            binding.spinnerCategory.adapter = adapter
        }

        binding.spinnerCategory.onItemSelectedListener =
            SpinnerOnItemSelectedListener(category) { category ->
                category?.let {
                    selectedCategory = it
                }
            }


    }

    private fun seekbarFunction() {
        binding.sbHome.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                binding.tvNumber.text = progress.toString()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }

        })
    }

    override fun setupSubscribes() {
        subscribeCategories()
    }

    private fun subscribeCategories() {
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

    override fun setupListeners() {
        binding.btnStart.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToQuestionFragment(
                    selectedCategory
                )
            )
        }
    }
}