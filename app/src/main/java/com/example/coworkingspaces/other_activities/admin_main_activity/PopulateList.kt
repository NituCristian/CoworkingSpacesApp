package com.example.coworkingspaces.other_activities.admin_main_activity

import com.example.coworkingspaces.R

class PopulateList {

    companion object Factory {

        fun getCards(dataSource: ArrayList<HomeCard>) {
            dataSource.clear()
            dataSource.add(
                   HomeCard(
                       "",
                            R.drawable.pricing_plans,
                            "Subscription Packages"
                    )
            )
            dataSource.add(
                  HomeCard(
                      "",
                          R.drawable.event,
                            "Events"
                    )
            )
        }
    }
}