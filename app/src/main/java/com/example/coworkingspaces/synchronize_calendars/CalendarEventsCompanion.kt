package com.example.coworkingspaces.synchronize_calendars

import android.graphics.Color
import com.applandeo.materialcalendarview.EventDay
import com.example.coworkingspaces.R
import java.util.Calendar

class CalendarEventsCompanion {
    companion object {

        var events = ArrayList<EventDay>()

        fun addFirstDays() {
            var selDay1: Calendar = Calendar.getInstance()
            selDay1.set(2021,0,11)
//            calendars.add(selDay1)
//            calendarView.setHighlightedDays(calendars)
            events.add(EventDay(selDay1, R.drawable.baseline_event_black_24dp, Color.parseColor("#228B22")))

            var selDay2: Calendar = Calendar.getInstance()
            selDay2.set(2021,0,12)
//            calendars.add(selDay2)
//            calendarView.setHighlightedDays(calendars)

            events.add(EventDay(selDay2, R.drawable.baseline_event_black_24dp, Color.parseColor("#228B22")))
        }

        init {
            var selDay1: Calendar = Calendar.getInstance()
            selDay1.set(2021,0,11)
//            calendars.add(selDay1)
//            calendarView.setHighlightedDays(calendars)
            events.add(EventDay(selDay1, R.drawable.baseline_event_black_24dp, Color.parseColor("#228B22")))

            var selDay2: Calendar = Calendar.getInstance()
            selDay2.set(2021,0,12)
//            calendars.add(selDay2)
//            calendarView.setHighlightedDays(calendars)


            events.add(EventDay(selDay2, R.drawable.baseline_event_black_24dp, Color.parseColor("#228B22")))
        }



        fun addEventDays(days: ArrayList<EventDay>){
            events.addAll(days)
        }

        fun populate(): ArrayList<EventDay> {

            return events
        }
    }
}