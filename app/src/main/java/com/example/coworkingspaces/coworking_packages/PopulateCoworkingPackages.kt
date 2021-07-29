package com.example.coworkingspaces.coworking_packages

import android.util.Log
import com.example.coworkingspaces.R

class PopulateCoworkingPackages {
    companion object {
        var selectionStatus = ArrayList<Boolean>()
        var coworkingPackages = ArrayList<CoworkingPackage>()

        var canAdd = true;

        init {
            selectionStatus.add(false)
            selectionStatus.add(false)
            selectionStatus.add(false)
        }

        fun modifyStatus() {
            var i = 0


            for(p in coworkingPackages) {

                Log.d("a", "" +  p.selected)
                p.selected = selectionStatus[i]

                i++
            }
        }

        fun addNewPackage(imageInt: Int, title: String, description: String, price: Float, features: ArrayList<String>) {
            selectionStatus.add(false)


            coworkingPackages.add(
                CoworkingPackage(
                    imageInt,
                    title,
                    description,
                    price,
                    selectionStatus[selectionStatus.size - 1],
                    features
                )
            )
        }

        fun populate(): ArrayList<CoworkingPackage> {

            if(canAdd == true) {
                coworkingPackages.add(
                    CoworkingPackage(
                        R.drawable.illustration_three,
                        "Starter coworking package",
                        "This package includes the most important " +
                                "features like your own seat, free coffee and high speed wi-fi.",
                        300.0f,
                        selectionStatus[0],
                        arrayListOf("personal seat", "free coffee", "high speed wi-fi")
                    )
                )
                coworkingPackages.add(
                    CoworkingPackage(
                        R.drawable.illustration_one,
                        "Standard coworking package",
                        "The package includes everything from the starter pack " +
                                "plus access to printers, meeting rooms and monitors.",
                        500.0f,
                        selectionStatus[1],
                        arrayListOf("printer access", "meeting rooms", "extra monitor")
                    )
                )
                coworkingPackages.add(
                    CoworkingPackage(
                        R.drawable.illustration_four,
                        "Premium coworking package",
                        "Premium coworking package has in addition a parking spot, 24/7 access and a locker.",
                        650.0f,
                        selectionStatus[2],
                        arrayListOf("parking spot for bike/scooter", "24/7 access", "small locker")
                    )
                )
                canAdd = false
            }

            return coworkingPackages
        }
    }
}