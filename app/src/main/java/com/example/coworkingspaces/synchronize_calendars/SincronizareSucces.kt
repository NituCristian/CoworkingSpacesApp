package com.example.coworkingspaces.synchronize_calendars

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.applandeo.materialcalendarview.EventDay
import com.example.coworkingspaces.R
import java.util.Calendar

class SincronizareSucces: Fragment() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_sincronizare_succes, container, false)

        var events: ArrayList<EventDay>
        //events = arguments?.getSerializable("events") as ArrayList<EventDay>
        events = ArrayList<EventDay>()

        val check = root.findViewById<ImageView>(R.id.sincronizare_succes_imageView)
        val success = root.findViewById<TextView>(R.id.sincronizare_succes_textView)
        val creating = root.findViewById<TextView>(R.id.textViewSincronizareSuccesWait)
        val progress = root.findViewById<ProgressBar>(R.id.progressBarSincronizareFail)
        val revenireButton = root.findViewById<Button>(R.id.revenire_calendar_button)
        check.visibility = View.GONE
        success.visibility = View.GONE
        revenireButton.visibility = View.GONE
//        creating.text = "Creating package \n\n  Please wait..."
//        success.text = "The package was successfully created!"

        val runable = Runnable {
            check.visibility = View.VISIBLE
            success.visibility = View.VISIBLE
            revenireButton.visibility = View.VISIBLE
            creating.visibility = View.GONE
            progress.visibility = View.GONE
        }

        Handler(Looper.getMainLooper()).postDelayed(runable, 3000)

        var selDay1: Calendar = Calendar.getInstance()
        selDay1.set(2021,0,13)
        events.add(EventDay(selDay1, R.drawable.baseline_event_black_24dp, Color.parseColor("#1F5BF3")))

        var selDay2: Calendar = Calendar.getInstance()
        selDay2.set(2021,0,14)
        events.add(EventDay(selDay2, R.drawable.baseline_event_black_24dp, Color.parseColor("#1F5BF3")))

        var selDay3: Calendar = Calendar.getInstance()
        selDay3.set(2021,0,15)
        events.add(EventDay(selDay3, R.drawable.baseline_event_black_24dp, Color.parseColor("#1F5BF3")))

        revenireButton.setOnClickListener(View.OnClickListener {


            var fragment: Fragment = com.example.coworkingspaces.synchronize_calendars.Calendar()
            var b: Bundle = Bundle()
            b.putSerializable("events", events)
            fragment.arguments = b
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