package com.ayush.campuseaze.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.campuseaze.data.model.Stays
import com.ayush.campuseaze.data.repository.DetailRepository
import com.ayush.campuseaze.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailRepository: DetailRepository
): ViewModel() {

    val stayDetail = MutableStateFlow<State<Stays>>(State.None)

    fun getStayDetail(stayId: String) {
        viewModelScope.launch {
            detailRepository.getStayDetails(stayId)
                .collect {
                    stayDetail.value = it
                }
        }
    }
}