package com.ayush.campuseaze.data.repository

import com.ayush.campuseaze.data.api.ApiService
import com.ayush.campuseaze.data.model.User
import com.ayush.campuseaze.utils.Constants.ERROR
import com.ayush.campuseaze.utils.State
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val auth: FirebaseAuth,
    private val retrofit: Retrofit
) {
    fun login(email: String, password: String): Flow<State<Boolean>> = callbackFlow {
        try {
            trySend(State.None)
            trySend(State.Loading)
            auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener {
                    trySend(State.Success(true))
                }
                .addOnFailureListener {
                    trySend(State.Error(it.message ?: ERROR))
                    trySend(State.None)
                }
        } catch (e: Exception) {
            trySend(State.Error(e.message ?: ERROR))
            trySend(State.None)
        }
        awaitClose {
            this.cancel()
        }
    }

    fun signUp(user: User, password: String): Flow<State<Boolean>> = callbackFlow {
        try {
            trySend(State.None)
            trySend(State.Loading)

            auth.createUserWithEmailAndPassword(user.email, password)
                .addOnSuccessListener { task ->
                    //Get user id
                    val newUser = user.copy(_id = task.user?.uid!!)

                    val api = retrofit.create(ApiService::class.java)
                    //Make api call
                    CoroutineScope(Dispatchers.IO).launch {
                        val apiCall = api.addUser(newUser)
                        if(apiCall.isSuccessful) {
                            trySend(State.Success(true))
                        } else {
                            trySend(State.Error(ERROR))
                            trySend(State.None)
                        }
                    }
                }
                .addOnFailureListener {
                    trySend(State.Error(it.message ?: ERROR))
                    trySend(State.None)
                }

        } catch (e: Exception) {
            trySend(State.Error(e.message ?: ERROR))
            trySend(State.None)
        }
        awaitClose {
            this.cancel()
        }
    }

    fun isLoggedIn() = auth.currentUser?.uid != null

    fun logOut() {
        auth.signOut()
    }
}