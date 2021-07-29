package com.example.coworkingspaces.choose_seat

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.example.coworkingspaces.R
import com.example.coworkingspaces.other_activities.user_main_activity.UserHomePageActivity
import com.example.coworkingspaces.miscellaneous.ActivityNavigation
import com.example.coworkingspaces.coworking_packages.ChosenReservation
import com.google.android.material.bottomnavigation.BottomNavigationView

class ChooseSeatActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var floorOneButton: Button
    private lateinit var floorTwoButton: Button
    private lateinit var floorThreeButton: Button
    private var floor: Int = 1

    private lateinit var floorImageView: ImageView
    private lateinit var locationEditText: EditText
    private var location: Int = 0

    private lateinit var optionOneCheckBox: CheckBox
    private lateinit var optionTwoCheckBox: CheckBox
    private lateinit var optionThreeCheckBox: CheckBox

    private lateinit var checkoutButton: Button
    private lateinit var priceTextView: TextView
    private var price: Float = 0.0f

    private lateinit var animation: LottieAnimationView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_seat)

        floorOneButton = findViewById(R.id.choose_seat_floor_1_button)
        floorOneButton.setOnClickListener(this)
        floorTwoButton = findViewById(R.id.choose_seat_floor_2_button)
        floorTwoButton.setOnClickListener(this)
        floorThreeButton = findViewById(R.id.choose_seat_floor_3_button)
        floorThreeButton.setOnClickListener(this)

        floorImageView = findViewById(R.id.choose_seat_floor_image_view)
        locationEditText = findViewById(R.id.choose_seat_location_edit_text)

        optionOneCheckBox = findViewById(R.id.choose_seat_checkbox_1)
        optionOneCheckBox.setOnClickListener(this)
        optionTwoCheckBox = findViewById(R.id.choose_seat_checkbox_2)
        optionTwoCheckBox.setOnClickListener(this)
        optionThreeCheckBox = findViewById(R.id.choose_seat_checkbox_3)
        optionThreeCheckBox.setOnClickListener(this)

        checkoutButton = findViewById(R.id.choose_seat_check_out_button)
        checkoutButton.setOnClickListener(this)
        priceTextView = findViewById(R.id.choose_seat_price_text_view)

        animation = findViewById(R.id.choose_seat_animation)
        animation.visibility = View.GONE

        updateFloor()

        bottomNavigationView = findViewById(R.id.bottomNav)
        val activityNavigation = ActivityNavigation(bottomNavigationView, this)
        activityNavigation.navigate()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    fun updateFloor() {
        if (1 in ChosenReservation.reservedOnFloor) {
            floorImageView.setImageDrawable(
                this.getDrawable(
                    R.drawable.floor_1_after
                )
            )
        } else {
            floorImageView.setImageDrawable(
                this.getDrawable(
                    R.drawable.floor_1
                )
            )
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.choose_seat_floor_1_button -> {
                    if (1 in ChosenReservation.reservedOnFloor) {
                        floorImageView.setImageDrawable(
                            this.getDrawable(
                                R.drawable.floor_1_after
                            )
                        )
                    } else {
                        floorImageView.setImageDrawable(
                            this.getDrawable(
                                R.drawable.floor_1
                            )
                        )
                    }
                    floor = 1
                }

                R.id.choose_seat_floor_2_button -> {
                    if (2 in ChosenReservation.reservedOnFloor) {
                        floorImageView.setImageDrawable(
                            this.getDrawable(
                                R.drawable.floor_2_after
                            )
                        )
                    } else {
                        floorImageView.setImageDrawable(
                            this.getDrawable(
                                R.drawable.floor_2
                            )
                        )
                    }
                    floor = 2
                }

                R.id.choose_seat_floor_3_button -> {
                    if (3 in ChosenReservation.reservedOnFloor) {
                        floorImageView.setImageDrawable(
                            this.getDrawable(
                                R.drawable.floor_3_after
                            )
                        )
                    } else {
                        floorImageView.setImageDrawable(
                            this.getDrawable(
                                R.drawable.floor_3
                            )
                        )
                    }
                    floor = 3
                }

                R.id.choose_seat_checkbox_1 -> {
                    if (optionOneCheckBox.isChecked) {
                        price += 100
                    } else {
                        price -= 100
                    }; priceTextView.text = "$price lei"
                }
                R.id.choose_seat_checkbox_2 -> {
                    if (optionTwoCheckBox.isChecked) {
                        price += 150
                    } else {
                        price -= 150
                    }; priceTextView.text = "$price lei"
                }
                R.id.choose_seat_checkbox_3 -> {
                    if (optionThreeCheckBox.isChecked) {
                        price += 50
                    } else {
                        price -= 50
                    }; priceTextView.text = "$price lei"
                }

                R.id.choose_seat_check_out_button -> {
                    if (locationEditText.text.toString() != "")
                        location = Integer.parseInt(locationEditText.text.toString())
                    showConfirmationDialog()
                }
            }
        }
    }

    private val showConfirmationAnimation = {
        animation.visibility = View.VISIBLE
        animation.playAnimation()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                animation.visibility = View.GONE
                val intent = Intent(this, UserHomePageActivity::class.java)
                ContextCompat.startActivity(this, intent, null)
            },
            2200
        )
    }

    private fun showConfirmationDialog() {
        if (floor == 0 || location == 0 || !locationValidator(floor, location)) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Check-out failed")
                .setMessage(
                    "Both floor and location must be selected and valid."
                )
                .setPositiveButton(
                    " Ok ", null
                )
            builder.create().show()
        } else {
            var optionsSelected = ""
            if (optionOneCheckBox.isChecked) {
                optionsSelected += "\n\u25CF "
                optionsSelected += optionOneCheckBox.text
            }
            if (optionTwoCheckBox.isChecked) {
                optionsSelected += "\n\u25CF "
                optionsSelected += optionTwoCheckBox.text
            }
            if (optionThreeCheckBox.isChecked) {
                optionsSelected += "\n\u25CF "
                optionsSelected += optionThreeCheckBox.text
            }

            var options = ""
            if (optionsSelected.isEmpty())
                options = "No options selected."
            else
                options = "Selected options: $optionsSelected"

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirm payment")
                .setMessage(
                    "Location $location on floor $floor has been selected.\n" +
                            options +
                            "\nYou have to pay $price lei."
                )
                .setPositiveButton(
                    " Confirm "
                ) { _: DialogInterface, _: Int ->
                    showConfirmationAnimation()
                    ChosenReservation.chosen = true
                    ChosenReservation.reservationType = "package"
                    ChosenReservation.floor = floor
                    ChosenReservation.location = location
                    ChosenReservation.extraOptionsSeat = getSelectedOptions()
                    ChosenReservation.reservedOnFloor.add(floor)
                }
                .setNegativeButton(
                    " Cancel ", null
                )
            builder.create().show()
        }
    }

    private fun getSelectedOptions(): ArrayList<String> {
        val options = ArrayList<String>()

        if (optionOneCheckBox.isChecked)
            options.add(optionOneCheckBox.text as String)
        if (optionTwoCheckBox.isChecked)
            options.add(optionTwoCheckBox.text as String)
        if (optionThreeCheckBox.isChecked)
            options.add(optionThreeCheckBox.text as String)

        return options
    }

    fun locationValidator(floor: Int, location: Int): Boolean {
        if (floor in ChosenReservation.reservedOnFloor && floor == 1)
            return location in intArrayOf(1, 2, 4, 6, 8, 13)
        else if (floor !in ChosenReservation.reservedOnFloor && floor == 1)
            return location == 11

        if (floor in ChosenReservation.reservedOnFloor && floor == 2)
            return location in intArrayOf(1, 2)
        else if (floor !in ChosenReservation.reservedOnFloor && floor == 2)
            return location == 3

        if (floor in ChosenReservation.reservedOnFloor && floor == 3)
            return location in intArrayOf(3, 4, 6)
        else if (floor !in ChosenReservation.reservedOnFloor && floor == 3)
            return location == 5

        return true
    }
}
