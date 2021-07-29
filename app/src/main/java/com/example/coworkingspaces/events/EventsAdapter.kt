package com.example.coworkingspaces.events

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.coworkingspaces.R

class EventsAdapter(private var context: Context, private val dataSource: ArrayList<Event_Card>): BaseAdapter() {
    private val inflator: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val rowView = inflator.inflate(R.layout.event_details_card, parent,false)

        val eventImageView= rowView.findViewById<ImageView>(R.id.imageViewEventCard)
        val eventTitle= rowView.findViewById<TextView>(R.id.textViewEventTitleCard)
        val eventDate= rowView.findViewById<TextView>(R.id.textViewEventDateTimeCard)
        val eventVenue= rowView.findViewById<TextView>(R.id.textViewEventVenueCard)
        val eventDetails= rowView.findViewById<TextView>(R.id.textViewEventDetailsCard)

        val card: Event_Card = dataSource[position]

        Glide.with(rowView).load(card.image).into(eventImageView)
        eventTitle.setText(card.name)
        eventDate.setText(card.dateTime)
        eventVenue.setText(card.venue)
        eventDetails.setText(card.description)
        return rowView
    }
}