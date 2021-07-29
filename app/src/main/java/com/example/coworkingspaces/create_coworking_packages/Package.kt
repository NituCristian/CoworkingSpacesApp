package com.example.coworkingspaces.create_coworking_packages

import com.example.coworkingspaces.R


object Package {
    var name: String = ""
    var description : String = ""
    var price : String = ""
    var currency : String = ""
    var features: MutableList<String> = mutableListOf<String>()
    var image: Int = R.drawable.coworking_package

}