package com.example.coworkingspaces.virtual_tour

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.coworkingspaces.other_activities.authentication.LoginActivity
import com.example.coworkingspaces.R

class VirtualTourActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_virtual_tour)

        val backToLogin = findViewById<Button>(R.id.virtualTourToLoginButton)
        val virtualTourImage = findViewById<ImageView>(R.id.tourImageView)
        val backArrow = findViewById<ImageView>(R.id.backArrowImageView)
        val forthArrow = findViewById<ImageView>(R.id.forthArrowImageView)

        val roomName = findViewById<TextView>(R.id.roomNameId)
        val roomCapacity = findViewById<TextView>(R.id.roomCapacityId)
        val roomDescription = findViewById<TextView>(R.id.roomDescriptionId)
        val roomType = findViewById<TextView>(R.id.roomTypeValue)

        roomName.text = getString(R.string.nameRoom1)
        roomCapacity.text = getString(R.string.capacityRoom1)
        roomDescription.text = getString(R.string.descriptionRoom1)
        roomType.text = getString(R.string.typeRoom1)
        roomDescription.movementMethod = ScrollingMovementMethod()

        backArrow.setImageResource(R.drawable.back_icon)
        forthArrow.setImageResource(R.drawable.forth_icon)
        virtualTourImage.setImageResource(R.drawable.coworking1)

        backArrow.setOnClickListener {
            when (virtualTourImage.drawable.constantState) {
                this.resources.getDrawable(R.drawable.coworking1, null).constantState -> {
                    virtualTourImage.setImageResource(R.drawable.coworking3)
                    roomName.text = getString(R.string.nameRoom3)
                    roomCapacity.text = getString(R.string.capacityRoom3)
                    roomDescription.text = getString(R.string.descriptionRoom3)
                    roomType.text = getString(R.string.typeRoom3)
                    roomDescription.movementMethod = ScrollingMovementMethod()
                }
                this.resources.getDrawable(R.drawable.coworking2, null).constantState -> {
                    virtualTourImage.setImageResource(R.drawable.coworking1)
                    roomName.text = getString(R.string.nameRoom1)
                    roomCapacity.text = getString(R.string.capacityRoom1)
                    roomDescription.text = getString(R.string.descriptionRoom1)
                    roomType.text = getString(R.string.typeRoom1)
                    roomDescription.movementMethod = ScrollingMovementMethod()
                }
                else -> {
                    virtualTourImage.setImageResource(R.drawable.coworking2)
                    roomName.text = getString(R.string.nameRoom2)
                    roomCapacity.text = getString(R.string.capacityRoom2)
                    roomDescription.text = getString(R.string.descriptionRoom2)
                    roomType.text = getString(R.string.typeRoom2)
                    roomDescription.movementMethod = ScrollingMovementMethod()

                }
            }
        }

        forthArrow.setOnClickListener {
            when (virtualTourImage.drawable.constantState) {
                this.resources.getDrawable(R.drawable.coworking1, null).constantState -> {
                    virtualTourImage.setImageResource(R.drawable.coworking2)
                    roomName.text = getString(R.string.nameRoom2)
                    roomCapacity.text = getString(R.string.capacityRoom2)
                    roomType.text = getString(R.string.typeRoom2)
                    roomDescription.text = getString(R.string.descriptionRoom2)
                    roomDescription.movementMethod = ScrollingMovementMethod()
                }
                this.resources.getDrawable(R.drawable.coworking2, null).constantState -> {
                    virtualTourImage.setImageResource(R.drawable.coworking3)
                    roomName.text = getString(R.string.nameRoom3)
                    roomCapacity.text = getString(R.string.capacityRoom3)
                    roomDescription.text = getString(R.string.descriptionRoom3)
                    roomType.text = getString(R.string.typeRoom3)
                    roomDescription.movementMethod = ScrollingMovementMethod()
                }
                else -> {
                    virtualTourImage.setImageResource(R.drawable.coworking1)
                    roomName.text = getString(R.string.nameRoom1)
                    roomCapacity.text = getString(R.string.capacityRoom1)
                    roomDescription.text = getString(R.string.descriptionRoom1)
                    roomType.text = getString(R.string.typeRoom1)
                    roomDescription.movementMethod = ScrollingMovementMethod()
                }
            }
        }

        backToLogin.setOnClickListener {
            val intent = Intent(this@VirtualTourActivity, LoginActivity::class.java )
            startActivity(intent)
        }

    }
}