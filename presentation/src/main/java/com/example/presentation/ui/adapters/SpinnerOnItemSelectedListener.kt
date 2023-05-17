package com.example.presentation.ui.adapters

import android.view.View
import android.widget.AdapterView

class CategorySpinnerOnItemSelectedListener(
    private val category: Array<String>,
    private val onSpinnerItemClick: (category: Int?) -> Unit,
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (category[position]) {
            "All" -> onSpinnerItemClick(0)
            "General Knowledge" -> onSpinnerItemClick(9)
            "Entertainment: Books" -> onSpinnerItemClick(10)
            "Entertainment: Film" -> onSpinnerItemClick(11)
            "Entertainment: Music" -> onSpinnerItemClick(12)
            "Entertainment: Musicals & Theatres" -> onSpinnerItemClick(13)
            "Entertainment: Television" -> onSpinnerItemClick(14)
            "Entertainment: Video Games" -> onSpinnerItemClick(15)
            "Entertainment: Board Games" -> onSpinnerItemClick(16)
            "Science & Nature" -> onSpinnerItemClick(17)
            "Science: Computers" -> onSpinnerItemClick(18)
            "Science: Mathematics" -> onSpinnerItemClick(19)
            "Mythology" -> onSpinnerItemClick(20)
            "Sports" -> onSpinnerItemClick(21)
            "Geography" -> onSpinnerItemClick(22)
            "History" -> onSpinnerItemClick(23)
            "Politics" -> onSpinnerItemClick(24)
            "Art" -> onSpinnerItemClick(25)
            "Celebrities" -> onSpinnerItemClick(26)
            "Animals" -> onSpinnerItemClick(27)
            else -> onSpinnerItemClick(null)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        parent?.setSelection(0)
        onSpinnerItemClick(null)
    }
}
class DifficultySpinnerOnItemSelectedListener(
    private val difficulty: Array<String>,
    private val onSpinnerItemClick: (difficulty: String?) -> Unit
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (difficulty[position]) {
            "All" -> onSpinnerItemClick("")
            "Easy" -> onSpinnerItemClick("easy")
            "Medium" -> onSpinnerItemClick("medium")
            "Hard" -> onSpinnerItemClick("hard")
            else -> onSpinnerItemClick(null)
        }
    }
    override fun onNothingSelected(parent: AdapterView<*>?) {
        parent?.setSelection(0)
        onSpinnerItemClick(null)
    }
}