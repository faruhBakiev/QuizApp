package com.example.presentation.ui.adapters

import android.view.View
import android.widget.AdapterView


class SpinnerOnItemSelectedListener(
    private val category: Array<String>,
    private val difficulty: Array<String>,
    private val onSpinnerItemClick: (category: Int?) -> Unit,
    private val OnSpinnerItemClickDifficulty: (difficulty: Int?) -> Unit
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (category[position]) {
            "Any Category" -> onSpinnerItemClick(9)
            "General Knowledge" -> onSpinnerItemClick(10)
            "Entertainment: Books" -> onSpinnerItemClick(11)
            "Entertainment: Film" -> onSpinnerItemClick(12)
            "Entertainment: Music" -> onSpinnerItemClick(13)
            "Entertainment: Musicals & Theatres" -> onSpinnerItemClick(14)
            "Entertainment: Television" -> onSpinnerItemClick(15)
            "Entertainment: Video Games" -> onSpinnerItemClick(16)
            "Entertainment: Board Games" -> onSpinnerItemClick(17)
            "Science & Nature" -> onSpinnerItemClick(18)
            "Science: Computers" -> onSpinnerItemClick(19)
            "Science: Mathematics" -> onSpinnerItemClick(20)
            "Mythology" -> onSpinnerItemClick(21)
            "Sports" -> onSpinnerItemClick(22)
            "Geography" -> onSpinnerItemClick(23)
            "History" -> onSpinnerItemClick(24)
            "Politics" -> onSpinnerItemClick(25)
            "Art" -> onSpinnerItemClick(26)
            "Celebrities" -> onSpinnerItemClick(27)
            "Animals" -> onSpinnerItemClick(28)
            else -> onSpinnerItemClick(null)
        }
        when (difficulty[position]) {
            "Any Difficulty" -> OnSpinnerItemClickDifficulty(1)
            "Easy" -> OnSpinnerItemClickDifficulty(2)
            "Medium" -> OnSpinnerItemClickDifficulty(3)
            "Hard" -> OnSpinnerItemClickDifficulty(4)

        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        parent?.setSelection(0)
        onSpinnerItemClick(null)
    }
}