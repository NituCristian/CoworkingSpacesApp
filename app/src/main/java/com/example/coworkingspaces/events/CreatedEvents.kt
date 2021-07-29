package com.example.coworkingspaces.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.coworkingspaces.R

class CreatedEvents: Fragment() {

    private lateinit var cardsAdapter: EventsAdapter;

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_events, container, false)


        val cardsListView = root.findViewById<ListView>(R.id.eventsList)
        val noevents = root.findViewById<TextView>(R.id.textViewNoEvents)

        cardsAdapter = EventsAdapter(this.requireContext(), Event.dataSource)
        cardsListView.adapter = cardsAdapter

        if (cardsListView.getCount()>0){
            noevents.text=""
        }

        return root
    }

}