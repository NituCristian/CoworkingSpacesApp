package com.example.coworkingspaces.coworking_packages

class ChosenReservation {
    companion object{
        var putSelectedOnFalse = false;
        var isExistentPackage = true;
        var chosen = false
        var reservedOnFloor = ArrayList<Int>()
        var reservationType: String = ""
        var packageName: String = ""
        var extraOptionsPackage: ArrayList<String> = ArrayList()
        var extraOptionsSeat: ArrayList<String> = ArrayList()
        var initialDate: String = ""
        var finalDate: String = ""
        var floor: Int = 0
        var location: Int = 0
    }
}