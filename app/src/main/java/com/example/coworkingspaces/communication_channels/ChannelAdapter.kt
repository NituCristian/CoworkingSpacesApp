package com.example.coworkingspaces.communication_channels

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.coworkingspaces.R

class ChannelAdapter(private var context: Context,  private val dataSource: ArrayList<Channel>): BaseAdapter(){

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
        val rowView = inflator.inflate(R.layout.communication_channel, parent,false)

        val channelImageView= rowView.findViewById<ImageView>(R.id.imageViewChannel)
        val channelTextView= rowView.findViewById<TextView>(R.id.textViewChannel)

        val card: Channel = dataSource[position]

        channelImageView.setImageDrawable((context.getDrawable(card.image)))
        channelTextView.setText(card.channel)

        return rowView
    }
}