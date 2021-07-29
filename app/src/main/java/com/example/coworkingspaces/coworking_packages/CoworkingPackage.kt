package com.example.coworkingspaces.coworking_packages

import java.io.Serializable

class CoworkingPackage(
    val imageId: Int,
    val title: String,
    val description: String,
    val price: Float,
    var selected: Boolean,
    var features: ArrayList<String> = ArrayList<String>()
) : Serializable {
}