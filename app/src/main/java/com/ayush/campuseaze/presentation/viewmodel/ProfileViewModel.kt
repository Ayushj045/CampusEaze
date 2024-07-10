package com.ayush.campuseaze.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.campuseaze.data.model.User
import com.ayush.campuseaze.data.repository.ProfileRepository
import com.ayush.campuseaze.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val profileRepository: ProfileRepository
) : ViewModel() {

    val user = MutableStateFlow<State<User>>(State.None)

    fun getUser(userId: String) {
        viewModelScope.launch {
            profileRepository.getUser(userId)
                .collect {
                    user.value = it
                }
        }
    }
}