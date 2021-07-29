package com.example.coworkingspaces.other_activities.user_account

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.coworkingspaces.R
import com.example.coworkingspaces.other_activities.authentication.LoginActivity
import com.example.coworkingspaces.miscellaneous.ActivityNavigation
import com.example.coworkingspaces.coworking_packages.ChosenReservation
import com.example.coworkingspaces.qr_based_leaving.LeaveSpaceActivity
import com.example.coworkingspaces.voice_assistant_setup.Asistent_Vocal_Settings
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter


class MyAccountActivity : AppCompatActivity() {

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_account)


        val firstName = findViewById<TextView>(R.id.firstNameValue)
        val lastName = findViewById<TextView>(R.id.lastNameValue)
        val email = findViewById<TextView>(R.id.emailTextValue)
        val phone = findViewById<TextView>(R.id.phoneValue)
        val birthdate = findViewById<TextView>(R.id.birthdateValue)

        val roomActiveTextView = findViewById<TextView>(R.id.roomTextView)
        val seatActiveTextView = findViewById<TextView>(R.id.deskTextView)
        val dateActiveTextView = findViewById<TextView>(R.id.dateTextView)
        val intervalActiveTextView = findViewById<TextView>(R.id.intervalTextView)

        val bookingsTextView = findViewById<TextView>(R.id.bookingsTextView)


        val currentHour = findViewById<TextView>(R.id.currentHourTextView)
        val logOutButton = findViewById<Button>(R.id.logOutButton)
        val assistantButton = findViewById<Button>(R.id.buttonAsistentVocal)

        val roomBooking = findViewById<TextView>(R.id.roomValue)
        val seatBooking = findViewById<TextView>(R.id.deskValue)
        val dateBooking = findViewById<TextView>(R.id.dateValue)
        val intervalBooking = findViewById<TextView>(R.id.intervalValue)

        val accountImage = findViewById<ImageView>(R.id.accountImageView)

        val leaveSpaceButton = findViewById<Button>(R.id.leaveSpaceButton)

        val packageTextView = findViewById<TextView>(R.id.packageTextView)
        val activePackageValue = findViewById<TextView>(R.id.activePackageValue)

        val packageDateTextView = findViewById<TextView>(R.id.packageDateTextView)
        val startDate = findViewById<TextView>(R.id.startDateValue)
        val endDate = findViewById<TextView>(R.id.endDateValue)

        val packageNameTextView = findViewById<TextView>(R.id.packageNameTextView)
        val packageNameValue = findViewById<TextView>(R.id.packageNameValue)

        val packageLocationTextView = findViewById<TextView>(R.id.packageLocationTextView)
        val packageLocationValue = findViewById<TextView>(R.id.packageLocationValue)

        val packageFloorTextView = findViewById<TextView>(R.id.packageFloorTextView)
        val packageFloorValue = findViewById<TextView>(R.id.packageFloorValue)

        val packageOptionsTextView = findViewById<TextView>(R.id.packageOptionsTextView)
        val extraOptionsPackage = findViewById<TextView>(R.id.extraOptionsPackageValue)

        val seatOptionsTextView = findViewById<TextView>(R.id.seatOptionsTextView)
        val extraOptionsSeat = findViewById<TextView>(R.id.seatOptionsPackageValue)

        firstName.text = UserInfoSingleton.name
        lastName.text = UserInfoSingleton.lastname
        email.text = UserInfoSingleton.email
        phone.text = UserInfoSingleton.phone
        birthdate.text = UserInfoSingleton.birthdate


        val removePackage = findViewById<Button>(R.id.removePackageId)

        if(UserInfoSingleton.name == "Andrei") {
            accountImage.setImageResource(R.drawable.acc)
        }

        else if(UserInfoSingleton.name == "Denisa") {
            accountImage.setImageResource(R.drawable.girl1)
        }

        else if(UserInfoSingleton.name == "Andrada") {
            accountImage.setImageResource(R.drawable.girl2)
        }

        val timer: CountDownTimer = object : CountDownTimer(1000000000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                currentHour.text = DateTimeFormatter
                    .ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.of("Europe/Bucharest"))
                    .format(Instant.now())
            }

            override fun onFinish() {}

        };

        timer.start()

        removePackage.setOnClickListener {
            ChosenReservation.reservationType = ""
            ChosenReservation.putSelectedOnFalse = true;

            val remove = { _: DialogInterface, _: Int ->

                val intent = Intent(this@MyAccountActivity, MyAccountActivity::class.java)
                startActivity(intent)
                finish()
            }

            val builder = AlertDialog.Builder(this)
            builder.setTitle(" Confirmation ")
                .setMessage("You are about to remove your package. Are you sure? ")
                .setPositiveButton(" Yes ", DialogInterface.OnClickListener(function = remove))
                .setNegativeButton(" No ", null)
            builder.create().show()


        }

        logOutButton.setOnClickListener {

            val logout = { _: DialogInterface, _: Int ->

                val intent = Intent(this@MyAccountActivity, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }

            val builder = AlertDialog.Builder(this)
            builder.setTitle(" Confirmation ")
                .setMessage("You are about to log out. Are you sure? ")
                .setPositiveButton(" Yes ", DialogInterface.OnClickListener(function = logout))
                .setNegativeButton(" No ", null)
            builder.create().show()

        }

        assistantButton.setOnClickListener {
            val intent = Intent(this@MyAccountActivity, Asistent_Vocal_Settings::class.java)
            startActivity(intent)
        }

        if(UserInfoSingleton.name != "Andrei") {
            bookingsTextView.visibility = View.GONE
            roomActiveTextView.visibility = View.GONE
            seatActiveTextView.visibility = View.GONE
            dateActiveTextView.visibility = View.GONE
            intervalActiveTextView.visibility = View.GONE
            roomBooking.visibility = View.GONE
            seatBooking.visibility = View.GONE
            dateBooking.visibility = View.GONE
            intervalBooking.visibility = View.GONE
            leaveSpaceButton.visibility = View.GONE
        }

        else {

            roomBooking.text = "A2"
            seatBooking.text = "21";
            dateBooking.text = "12/1/2021"
            intervalBooking.text = "10:00-18:00"



            leaveSpaceButton.setOnClickListener {

                val qrScan = { _: DialogInterface, _: Int ->

                    bookingsTextView.visibility = View.GONE
                    roomActiveTextView.visibility = View.GONE
                    seatActiveTextView.visibility = View.GONE
                    dateActiveTextView.visibility = View.GONE
                    intervalActiveTextView.visibility = View.GONE
                    roomBooking.visibility = View.GONE
                    seatBooking.visibility = View.GONE
                    dateBooking.visibility = View.GONE
                    intervalBooking.visibility = View.GONE
                    leaveSpaceButton.visibility = View.GONE

                    val intent = Intent(this@MyAccountActivity, LeaveSpaceActivity::class.java)
                    startActivity(intent)
                    finish()
                }

                val builder = AlertDialog.Builder(this)
                builder.setTitle(" Confirmation ")
                    .setMessage(" Are you sure? ")
                    .setPositiveButton(" Yes ", DialogInterface.OnClickListener(function = qrScan))
                    .setNegativeButton(" No ", null)
                builder.create().show()
            }
        }

        if(ChosenReservation.reservationType == "") {
            packageDateTextView.visibility = View.GONE
            packageTextView.visibility = View.GONE
            packageNameTextView.visibility = View.GONE
            packageLocationTextView.visibility = View.GONE
            packageFloorTextView.visibility = View.GONE
            packageOptionsTextView.visibility = View.GONE
            seatOptionsTextView.visibility = View.GONE
            removePackage.visibility = View.GONE
        }

        else {

            activePackageValue.text = ChosenReservation.reservationType


            packageTextView.visibility = View.VISIBLE
            packageNameTextView.visibility = View.GONE
            packageDateTextView.visibility = View.GONE
            startDate.visibility = View.GONE
            startDate.text = ChosenReservation.initialDate

            packageLocationTextView.visibility = View.VISIBLE
            packageLocationValue.text = ChosenReservation.location.toString()

            packageFloorTextView.visibility = View.VISIBLE
            packageFloorValue.text = ChosenReservation.floor.toString()

            seatOptionsTextView.visibility = View.VISIBLE

            for(seatOption in ChosenReservation.extraOptionsSeat) {
                extraOptionsSeat.text = extraOptionsSeat.text.toString() + '\u2022' + seatOption.toString() + '\n'
            }

            packageOptionsTextView.visibility = View.GONE

            if(ChosenReservation.reservationType == "one-time") {
                packageDateTextView.visibility = View.VISIBLE
                startDate.visibility = View.VISIBLE

            }


            if(ChosenReservation.reservationType == "package" && !ChosenReservation.isExistentPackage) {

                if(ChosenReservation.finalDate != "") {
                    endDate.text = " - " + ChosenReservation.finalDate
                }
                packageNameTextView.visibility = View.VISIBLE
                packageDateTextView.visibility = View.VISIBLE
                startDate.visibility = View.VISIBLE
                packageNameValue.text = ChosenReservation.packageName

                packageOptionsTextView.visibility = View.VISIBLE

                for(packageOption in ChosenReservation.extraOptionsPackage) {
                    extraOptionsPackage.text = extraOptionsPackage.text.toString() + '\u2022' + packageOption.toString() + '\n'
                }

            }
        }


        bottomNavigationView = findViewById(R.id.bottomNav)
        bottomNavigationView.selectedItemId = R.id.navigation_dashboard;


        val goToActivity = ActivityNavigation(bottomNavigationView, this)

        goToActivity.navigate()

    }
}