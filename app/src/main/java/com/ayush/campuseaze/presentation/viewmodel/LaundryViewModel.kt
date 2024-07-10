package com.ayush.campuseaze.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.campuseaze.data.model.Laundry
import com.ayush.campuseaze.data.repository.HomeRepository
import com.ayush.campuseaze.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LaundryViewModel @Inject constructor(
    private val homeRepository: HomeRepository
): ViewModel() {

    val allLaundries = MutableStateFlow<State<List<Laundry>>>(State.None)

    fun getAllLaundries() {
        viewModelScope.launch {
            homeRepository.getAllLaundries().collect {
                allLaundries.value = it
            }
        }
    }
}