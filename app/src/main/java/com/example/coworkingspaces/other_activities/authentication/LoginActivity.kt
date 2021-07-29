package com.example.coworkingspaces.other_activities.authentication

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.applandeo.materialcalendarview.EventDay
import com.example.coworkingspaces.other_activities.admin_main_activity.AdminHomePageActivity
import com.example.coworkingspaces.R
import com.example.coworkingspaces.communication_channels.ChannelSelected
import com.example.coworkingspaces.other_activities.user_main_activity.UserHomePageActivity
import com.example.coworkingspaces.synchronize_calendars.CalendarEventsCompanion
import com.example.coworkingspaces.other_activities.user_account.PopulateUsers
import com.example.coworkingspaces.coworking_packages.ChosenReservation
import com.example.coworkingspaces.coworking_packages.PopulateCoworkingPackages
import com.example.coworkingspaces.other_activities.user_account.UserInfoSingleton
import com.example.coworkingspaces.virtual_tour.VirtualTourActivity


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        PopulateUsers.populate()

        val loginTextView = findViewById<Button>(R.id.login)
        loginTextView.setOnClickListener(View.OnClickListener {
            val username = findViewById<EditText>(R.id.usrusr)
            val password = findViewById<EditText>(R.id.pswrdd)

            if (PopulateUsers.verifyUser(username.text.toString(), password.text.toString())) {

                if(UserInfoSingleton.name != PopulateUsers.getName(username.text.toString(), password.text.toString())) {

                    CalendarEventsCompanion.events = ArrayList<EventDay>()
                    CalendarEventsCompanion.addFirstDays()

                    ChosenReservation.reservationType = "";
                    ChosenReservation.isExistentPackage = true;
                    ChosenReservation.packageName = ""
                    ChosenReservation.extraOptionsPackage = ArrayList<String>()
                    ChosenReservation.extraOptionsSeat = ArrayList<String>()
                    ChosenReservation.initialDate = ""
                    ChosenReservation.finalDate = ""
                    ChosenReservation.floor = 0
                    ChosenReservation.location = 0



                    for(c in PopulateCoworkingPackages.coworkingPackages) {
                        c.selected = false
                    }
                }

                UserInfoSingleton.name = PopulateUsers.getName(username.text.toString(), password.text.toString())
                UserInfoSingleton.lastname = PopulateUsers.getLastName(username.text.toString(), password.text.toString())
                UserInfoSingleton.phone =  PopulateUsers.getPhone(username.text.toString(), password.text.toString())
                UserInfoSingleton.email =  PopulateUsers.getEmail(username.text.toString(), password.text.toString())
                UserInfoSingleton.birthdate =  PopulateUsers.getBirthdate(username.text.toString(), password.text.toString())
                UserInfoSingleton.username = username.text.toString()
                UserInfoSingleton.password = password.text.toString()

                ChannelSelected.chosen = false


                val intent = Intent(this, UserHomePageActivity::class.java)
                startActivity(intent)
            } else if (username.text.toString() == "admin" && password.text.toString() == "admin") {
                val intent = Intent(this, AdminHomePageActivity::class.java)
                startActivity(intent)
            } else {
                val builder1 = AlertDialog.Builder(this)
                builder1.setTitle("Authentication error!")
                builder1.setMessage("The username or password provided is wrong!")
                builder1.setCancelable(true)
                builder1.setPositiveButton(
                    "Retry"
                ) { dialog, which ->
                }
                val alert11 = builder1.create()
                alert11.show()
            }
        })

        val redirectTextView = findViewById<TextView>(R.id.redirect_singup)
        println("HEREEEE " + redirectTextView)

        redirectTextView.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        })

        val virtualTourButton = findViewById<Button>(R.id.makeVirtualTourButton)

        virtualTourButton.setOnClickListener {
            val intent = Intent(this@LoginActivity, VirtualTourActivity::class.java)
            startActivity(intent)
        }
    }
}