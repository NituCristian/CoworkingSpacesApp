package com.example.coworkingspaces.synchronize_calendars

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.applandeo.materialcalendarview.CalendarView
import com.applandeo.materialcalendarview.EventDay
import com.example.coworkingspaces.R
import java.util.Calendar


class Calendar : Fragment() {

    private lateinit var calendarView: CalendarView
    lateinit var events : ArrayList<EventDay>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val root =  inflater.inflate(R.layout.fragment_calendar, container, false)
        calendarView = root.findViewById<CalendarView>(R.id.calendarView)
        val sincronizare_button = root.findViewById<Button>(R.id.sincronizare_calendar_button)



        if(arguments==null){
            events = ArrayList<EventDay>()
        } else {
            events = arguments?.getSerializable("events") as ArrayList<EventDay>
            CalendarEventsCompanion.addEventDays(events)
        }

        arguments = null

        //val calendar: Calendar =  Calendar.getInstance()

        var min: Calendar = java.util.Calendar.getInstance()

        calendarView.setMinimumDate(min)

        //var calendars: ArrayList<Calendar> = ArrayList()

        calendarView.setEvents(CalendarEventsCompanion.events)

        sincronizare_button.setOnClickListener(View.OnClickListener {

            var fragment: Fragment = AlegereCalendar()
//            var b: Bundle = Bundle()
//            b.putSerializable("events", events)
//            fragment.arguments = b
            //  Navigation.findNavController(root).navigate(R.id.navigation_detaliiSala, b)
            replaceFragment(fragment)
        })

        return root
    }

    private fun replaceFragment(someFragment: Fragment?) {
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        if (someFragment != null) {
            transaction.replace(R.id.nav_host_fragment22, someFragment)
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }


}