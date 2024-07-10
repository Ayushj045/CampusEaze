package com.ayush.campuseaze.data.api

import com.ayush.campuseaze.data.model.Laundry
import com.ayush.campuseaze.data.model.Stays
import com.ayush.campuseaze.data.model.User
import com.ayush.campuseaze.utils.Constants.ENDPOINT_SIGNUP
import com.ayush.campuseaze.utils.Constants.GET_LAUNDRIES
import com.ayush.campuseaze.utils.Constants.GET_STAYS_BY_TYPE
import com.ayush.campuseaze.utils.Constants.GET_STAY_BY_ID
import com.ayush.campuseaze.utils.Constants.GET_USER
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {

    @POST(ENDPOINT_SIGNUP)
    suspend fun addUser(@Body user: User): Response<User>

    @GET(GET_USER)
    suspend fun getUser(@Path("id") id: String): Response<List<User>>

    @GET(GET_STAYS_BY_TYPE)
    suspend fun getStaysByType(@Path("type") type: String): Response<List<Stays>>

    @GET(GET_LAUNDRIES)
    suspend fun getLaundries(): Response<List<Laundry>>

    @GET(GET_STAY_BY_ID)
    suspend fun getStayById(@Path("id") id: String): Response<List<Stays>>
}