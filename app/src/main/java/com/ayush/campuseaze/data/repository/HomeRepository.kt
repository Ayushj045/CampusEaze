package com.ayush.campuseaze.data.repository

import com.ayush.campuseaze.data.api.ApiService
import com.ayush.campuseaze.data.model.Laundry
import com.ayush.campuseaze.data.model.Stays
import com.ayush.campuseaze.data.model.User
import com.ayush.campuseaze.utils.Constants.ERROR
import com.ayush.campuseaze.utils.State
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepository @Inject constructor(
    private val retrofit: Retrofit,
    private val auth: FirebaseAuth
) {
    fun getUserId() = auth.currentUser?.uid!!

    fun getStaysByType(item: String): Flow<State<List<Stays>>> = flow {
        emit(State.None)
        emit(State.Loading)
        val api = retrofit.create(ApiService::class.java)
        val apiCall = api.getStaysByType(item.lowercase())
        if (apiCall.isSuccessful) {
            emit(State.Success(apiCall.body()!!))
        } else {
            emit(State.Error(ERROR))
            emit(State.None)
        }
    }

    fun getAllLaundries(): Flow<State<List<Laundry>>> = flow {
        emit(State.None)
        emit(State.Loading)
        val api = retrofit.create(ApiService::class.java)
        val apiCall = api.getLaundries()
        if (apiCall.isSuccessful) {
            emit(State.Success(apiCall.body()!!))
        } else {
            emit(State.Error(ERROR))
            emit(State.None)
        }
    }
}