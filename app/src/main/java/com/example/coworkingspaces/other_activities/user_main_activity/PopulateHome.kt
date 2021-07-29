package com.example.coworkingspaces.other_activities.user_main_activity

import com.example.coworkingspaces.R
import com.example.coworkingspaces.other_activities.admin_main_activity.HomeCard

class PopulateHome {

    companion object Factory {

        fun getCards(dataSource: ArrayList<HomeCard>) {
            dataSource.clear()
            dataSource.add(
                HomeCard(
                    "Booking",
                    R.drawable.conference,
                    "Conference Rooms"
                )
            )
            dataSource.add(
                HomeCard(
                    "Booking",
                    R.drawable.seat,
                    "Individual Seats"
                )
            )

            dataSource.add(
                HomeCard(
                    "Booking",
                    R.drawable.modern_business,
                    "One time use tickets"
                )
            )
            dataSource.add(
                HomeCard(
                    "",
                    R.drawable.events,
                    "Events"
                )
            )
            dataSource.add(
                HomeCard(
                    "",
                    R.drawable.pricing_plans,
                    "Coworking Packages"
                )
            )
        }
    }
}