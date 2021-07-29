package com.example.coworkingspaces.other_activities.admin_main_activity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.coworkingspaces.R

class HomeAdapter(private var context: Context,  private val dataSource: ArrayList<HomeCard>): BaseAdapter() {
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
        val rowView = inflator.inflate(R.layout.home_card, parent,false)

        val homeImageView= rowView.findViewById<ImageView>(R.id.homeCardImage)
        val homeTextView= rowView.findViewById<TextView>(R.id.homeCardText)
        val catTextView= rowView.findViewById<TextView>(R.id.homeCardCategory)

        val card: HomeCard = dataSource[position]

        homeImageView.setImageDrawable((context.getDrawable(card.image)))
        homeTextView.setText(card.text)
        catTextView.setText(card.category)

        return rowView
    }
}