package com.ayush.campuseaze.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ayush.campuseaze.data.model.User
import com.ayush.campuseaze.data.repository.AuthRepository
import com.ayush.campuseaze.utils.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
): ViewModel() {

    val signIn = MutableStateFlow<State<Boolean>>(State.None)
    val signUp = MutableStateFlow<State<Boolean>>(State.None)

    fun signIn(email: String, password: String) {
        viewModelScope.launch {
            authRepository.login(email, password).collect {
                signIn.value = it
            }
        }
    }

    fun signUp(user: User, password: String) {
        viewModelScope.launch {
            authRepository.signUp(user, password).collect {
                signUp.value = it
            }
        }
    }

    fun isLoggedIn() = authRepository.isLoggedIn()

    fun logOut() {
        authRepository.logOut()
    }
}