package com.example.coworkingspaces.conference_room_booking

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.example.coworkingspaces.R

class SalaAdapter(private var context: Context, private val dataSource: ArrayList<SalaCard>): BaseAdapter() {
    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.sala_card, parent, false)

        var imageImageView = view.findViewById<ImageView>(R.id.card_image_view)
        var titleTextView = view.findViewById<TextView>(R.id.card_title_text_view)
        var descriptionTextView = view.findViewById<TextView>(R.id.card_description_text_view)
        var priceTextView = view.findViewById<TextView>(R.id.card_price_text_view)
        var selectedTextView = view.findViewById<TextView>(R.id.card_selected_text_view)


        val card: SalaCard = dataSource[position]

        imageImageView.setImageDrawable(
            AppCompatResources.getDrawable(
                context,
                card.imageId
            )
        )
        titleTextView.text = card.title
        descriptionTextView.text = card.description
        priceTextView.text = "Price: " + card.price.toString() + " lei"
        if (card.selected)
            selectedTextView.text = "Booked"
        else
            selectedTextView.text = "Free"

        return view
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

    fun setSelected(state: Boolean, position: Int){
        dataSource[position].selected = state
    }

}