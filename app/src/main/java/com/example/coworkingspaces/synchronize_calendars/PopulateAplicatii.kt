package com.example.coworkingspaces.synchronize_calendars

import com.example.coworkingspaces.R

class PopulateAplicatii {

    companion object Factory{
        fun getAplicatii(dataSource: ArrayList<AplicatieCalendar>){
            dataSource.clear()
            dataSource.add(
                AplicatieCalendar(
                    R.drawable.google_calendar,
                    "Google Calendar"
                )
            )
            dataSource.add(
                AplicatieCalendar(
                    R.drawable.microsoft_calendar,
                    "Microsoft Calendar"
                )
            )
        }
    }
}