package com.example.coworkingspaces.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.coworkingspaces.R

class EventActivity: Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.activity_events, container, false)
        val addEvent = root.findViewById<ImageView>(R.id.imageViewCreateEvent)
        val viewEvents = root.findViewById<ImageView>(R.id.imageViewViewEvents)

        addEvent.setOnClickListener(View.OnClickListener{
            var fragment: Fragment = EventCreationDetailsActivity()
            replaceFragment(fragment)
        })

        viewEvents.setOnClickListener(View.OnClickListener{
            var fragment: Fragment = CreatedEvents()
            replaceFragment(fragment)
        })

        return root
    }

    fun replaceFragment(someFragment: Fragment?) {
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        if (someFragment != null) {
            transaction.replace(R.id.nav_host_fragment, someFragment)
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }


}