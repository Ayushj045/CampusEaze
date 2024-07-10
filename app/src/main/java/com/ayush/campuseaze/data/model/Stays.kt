package com.ayush.campuseaze.data.model

data class Stays(
    val _id: String = "",
    val type: String = "",
    val name: String = "",
    val rent: Int = 0,
    val rating: Int = 0,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val phone: Long = 0L,
    val nearbyLandmark: String = "",
    val address :String = "",
)
