package com.example.presentation.ui.fragments.home

import androidx.lifecycle.viewModelScope
import com.example.domain.either.Either
import com.example.domain.usecases.FetchCategoriesUseCase
import com.example.presentation.base.BaseViewModel
import com.example.presentation.models.ResultsItemUI
import com.example.presentation.models.toUI
import com.example.presentation.state.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val fetchCategoriesUseCase: FetchCategoriesUseCase
) : BaseViewModel() {

    init {
        fetchCategory()
    }

    private val _categoryState =
        MutableStateFlow<UIState<List<ResultsItemUI>>>(UIState.Loading())
    val categoryState = _categoryState.asStateFlow()

    private fun fetchCategory() {
        viewModelScope.launch {
            fetchCategoriesUseCase( 9 ,"easy",7).collect{ either->
                when (either) {
                    is Either.Left -> {
                        either.message.let {
                            _categoryState.value = UIState.Error(either.message.toString())
                        }
                    }
                    is Either.Right -> {
                        either.data?.let { data->
                            _categoryState.value = UIState.Success(data.map {item->
                                item.toUI()
                            })
                        }
                    }
                }
            }
        }
    }
}