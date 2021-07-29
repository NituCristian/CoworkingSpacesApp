package com.example.coworkingspaces.conference_room_booking

import com.example.coworkingspaces.R

class PopulateList {
    companion object Factory {

        var selectionStatus = ArrayList<Boolean>()

        init {
            selectionStatus.add(false)
            selectionStatus.add(false)
            selectionStatus.add(false)
        }

        fun getCards(dataSource: ArrayList<SalaCard>) {
            dataSource.clear()
            dataSource.add(
                SalaCard(
                    R.drawable.illustration_three,
                    "Basic meeting room",
                    "This meeting room comes with 20 seats and offers all the basic needs for a meeting",

                    60.0f,
                    selectionStatus[0]
                )
            )
            dataSource.add(
                SalaCard(
                    R.drawable.illustration_one,
                    "Large meeting room",
                    "This meeting room houses 40 seats and offers all the basic needs for a meeting with some extra benefits" ,
                    120.0f,
                    selectionStatus[1]
                )
            )
            dataSource.add(
                SalaCard(
                    R.drawable.illustration_four,
                    "Complete meeting room",
                    "This meeting room houses 60 seats and offers all the basic needs for a meeting with some extra benefits" +
                            "with a variety of extra benefits such a smart board or full surround sound",
                    180.0f,
                    selectionStatus[2]
                )
            )
        }
    }
}