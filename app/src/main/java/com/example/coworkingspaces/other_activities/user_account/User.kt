package com.example.coworkingspaces.other_activities.user_account

class User(
    val lastName: String,
    val firstName: String,
    val username: String,
    val password: String,
    val phoneNumber: String,
    val email: String,
    val birthDate: String,
    val cardNumber: String,
    val cvv: String,
    val expDate: String,
    var packageSelected: Boolean = false
) {
}