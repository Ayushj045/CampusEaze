package com.ayush.campuseaze.data.repository

import com.ayush.campuseaze.data.api.ApiService
import com.ayush.campuseaze.data.model.Stays
import com.ayush.campuseaze.utils.State
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailRepository @Inject constructor(
    private val retrofit: Retrofit
) {
    fun getStayDetails(stayId: String): Flow<State<Stays>> = flow {
        emit(State.None)
        emit(State.Loading)

        val api = retrofit.create(ApiService::class.java)
        val apiCall = api.getStayById(stayId)

        if(apiCall.isSuccessful) {
            emit(State.Success(apiCall.body()!![0]))
        } else {
            emit(State.Error(apiCall.message()))
            emit(State.None)
        }
    }
}