package com.example.coworkingspaces.conference_room_booking

import java.io.Serializable

class SalaCard (
    val imageId: Int,
    val title: String,
    val description: String,
    val price: Float,
    var selected: Boolean
) : Serializable {
}