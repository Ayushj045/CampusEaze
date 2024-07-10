package com.ayush.campuseaze.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.campuseaze.data.model.Stays
import com.ayush.campuseaze.data.repository.HomeRepository
import com.ayush.campuseaze.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StaysViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {

    val staysByType = MutableStateFlow<State<List<Stays>>>(State.None)

    fun getUserId() = homeRepository.getUserId()

    fun getStaysByType(type: String) {
        Log.d("called", "type: $type")
        viewModelScope.launch {
            homeRepository.getStaysByType(type)
                .collect {
                    staysByType.value = it
                }
        }
    }

}