package com.example.coworkingspaces.synchronize_calendars

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.coworkingspaces.R

class AplicatieCalendarAdapter(private var context: Context, private val dataSource: ArrayList<AplicatieCalendar>): BaseAdapter() {


    private val inflator: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflator.inflate(R.layout.aplicatie_calendar_card, parent,false)

        val aplicatieImageView = rowView.findViewById<ImageView>(R.id.aplicatie_imageView)
        val aplicatieTextView = rowView.findViewById<TextView>(R.id.aplicatie_textView)

        val card: AplicatieCalendar = dataSource[position]

        aplicatieImageView.setImageDrawable(context.getDrawable(card.image))
        aplicatieTextView.text = card.aplicatie


        return rowView
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataSource.size
    }


}