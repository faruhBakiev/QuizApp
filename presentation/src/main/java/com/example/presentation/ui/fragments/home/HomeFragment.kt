package com.example.presentation.ui.fragments.home

import android.widget.ArrayAdapter
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.presentation.R
import com.example.presentation.base.BaseFragment
import com.example.presentation.databinding.FragmentHomeBinding
import com.example.presentation.ui.adapters.CategorySpinnerOnItemSelectedListener
import com.example.presentation.ui.adapters.DifficultySpinnerOnItemSelectedListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>(
    R.layout.fragment_home
) {
    override val binding by viewBinding(FragmentHomeBinding::bind)
    override val viewModel: HomeViewModel by viewModels()
    private var selectedCategory: Int = 0
    private var selectedDifficulty: String = ""
    private var selectedAmount: Int = 0

    private val category = arrayOf(
        "All",
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
        "All",
        "Easy",
        "Medium",
        "Hard"
    )

    override fun initialize() {
        spinnerCategory()
        spinnerDifficulty()
        seekbarFunction()
    }

    override fun setupListeners() {
        binding.btnStart.setOnClickListener {
            val seekbar = binding.sbHome.progress
            if (seekbar == 0){
                Toast.makeText(requireContext(), "Выберите кол-во вопросов!", Toast.LENGTH_SHORT).show()
            }else{
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToQuestionFragment(
                    selectedCategory,
                    selectedDifficulty,
                    selectedAmount
                )
            )
        }}
    }

    private fun spinnerCategory() {
        binding.spinnerCategory.setSelection(0)
        ArrayAdapter(
            requireContext(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item, category
        ).also { adapter ->
            adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
            binding.spinnerCategory.adapter = adapter
        }

        binding.spinnerCategory.onItemSelectedListener =
            CategorySpinnerOnItemSelectedListener(category) { category ->
                category?.let {
                    selectedCategory = it
                }
            }
    }

    private fun spinnerDifficulty() {
        binding.spinnerDifficulty.setSelection(0)
        ArrayAdapter(
            requireContext(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item, difficulty
        ).also { adapter ->
            adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item)
            binding.spinnerDifficulty.adapter = adapter
        }
        binding.spinnerDifficulty.onItemSelectedListener =
            DifficultySpinnerOnItemSelectedListener(difficulty) { difficulty ->
                difficulty?.let {
                    selectedDifficulty = it
                }
            }
    }

    private fun seekbarFunction() {
        binding.sbHome.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                binding.tvNumber.text = progress.toString()
                selectedAmount = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
            }
        })

    }
}