package com.example.coworkingspaces.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.coworkingspaces.R

class EventDetailsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.event_details_card, container, false)

        val name = root.findViewById<TextView>(R.id.textViewEventTitleCard)
        val dateTime = root.findViewById<TextView>(R.id.textViewEventDateTimeCard)
        val venue = root.findViewById<TextView>(R.id.textViewEventVenueCard)
        val details = root.findViewById<TextView>(R.id.textViewEventDetailsCard)
        val image = root.findViewById<ImageView>(R.id.imageViewEventCard)
        Glide.with(this).load(Event.image).into(image)
        name.text = Event.name
        dateTime.text = "\uD83D\uDCC6 " + Event.date + ", " + Event.start + "-" + Event.end
        venue.text = "\uD83D\uDCCC" + Event.venue
        details.text = Event.description
        return root
    }

}