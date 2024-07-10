package com.ayush.campuseaze.data.model

data class Laundry(
    val _id: String = "",
    val name: String = "",
    val address: String = "",
    val phone: Long = 0L,
    val price: Int = 0,
    val rating: Int = 0,
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val nearbyLandmark: String = ""
)
