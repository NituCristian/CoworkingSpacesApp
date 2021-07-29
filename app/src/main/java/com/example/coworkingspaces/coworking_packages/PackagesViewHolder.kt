package com.example.coworkingspaces.coworking_packages

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.coworkingspaces.R

class PackagesViewHolder(
    var coworkingPackages: ArrayList<CoworkingPackage>,
    val context: Context,
    view: View
) : RecyclerView.ViewHolder(view),
    View.OnClickListener {
    private var packagePosition: Int = 0
    private lateinit var coworkingPackage: CoworkingPackage

    private var imageImageView = view.findViewById<ImageView>(R.id.card_image_view)
    private var titleTextView = view.findViewById<TextView>(R.id.card_title_text_view)
    private var descriptionTextView = view.findViewById<TextView>(R.id.card_description_text_view)
    private var priceTextView = view.findViewById<TextView>(R.id.card_price_text_view)
    private var selectPackageButton = view.findViewById<Button>(R.id.card_view_package_button)
    private var selectedTextView = view.findViewById<TextView>(R.id.card_selected_text_view)

    init {
        selectPackageButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            if (v.id == R.id.card_view_package_button) {
                val intent = Intent(context, DetailedPackageActivity::class.java)
                intent.putExtra("packagePosition", packagePosition)
                intent.putExtra("coworkingPackage", coworkingPackages[packagePosition])
                startActivity(context, intent, null)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    fun bindData(packageObj: CoworkingPackage) {
        coworkingPackage = packageObj

        imageImageView.setImageDrawable(getDrawable(context, coworkingPackage.imageId))
        titleTextView.text = coworkingPackage.title
        descriptionTextView.text = coworkingPackage.description
        priceTextView.text = "Price: " + coworkingPackage.price.toString() + " lei"
        if (coworkingPackage.selected && !ChosenReservation.putSelectedOnFalse)
            selectedTextView.text = "Selected"
        else
            selectedTextView.text = "Unselected"
    }

    fun setPackagePosition(position: Int) {
        packagePosition = position
    }
}