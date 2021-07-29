package com.example.coworkingspaces.other_activities.user_main_activity

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.coworkingspaces.R
import com.example.coworkingspaces.conference_room_booking.ListaSaliActivity2
import com.example.coworkingspaces.miscellaneous.ActivityNavigation
import com.example.coworkingspaces.choose_seat.ChooseSeatActivity
import com.example.coworkingspaces.communication_channels.ChannelSelected
import com.example.coworkingspaces.one_time_use.OneTimeUseActivity
import com.example.coworkingspaces.coworking_packages.PackagesActivity
import com.example.coworkingspaces.events.CreatedEventsActivity
import com.example.coworkingspaces.qr_based_access.QrScanActivity
import com.example.coworkingspaces.other_activities.admin_main_activity.HomeAdapter
import com.example.coworkingspaces.other_activities.admin_main_activity.HomeCard
import com.example.coworkingspaces.other_activities.user_account.PopulateUsers
import com.example.coworkingspaces.other_activities.user_account.User
import com.example.coworkingspaces.other_activities.user_account.UserInfoSingleton
import com.google.android.material.bottomnavigation.BottomNavigationView

class UserHomePageActivity : AppCompatActivity() {

    private val dataSource = ArrayList<HomeCard>()
    private lateinit var cardsAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_home)


        val cardsListView = findViewById<ListView>(R.id.homeList)
        PopulateHome.getCards(dataSource)
        cardsAdapter = HomeAdapter(this, dataSource)
        cardsListView.adapter = cardsAdapter

        cardsListView.setOnItemClickListener { parent, view, position, id ->

            if(position == 0) {
                val i = Intent(this, ListaSaliActivity2::class.java)
                startActivity(i)
            }

            else if(position == 1) {
                if(PopulateUsers.hasPackageSelected(UserInfoSingleton.username , UserInfoSingleton.password)){
                    val i = Intent(this, ChooseSeatActivity::class.java)
                    startActivity(i)
                }else{
                    val builder = androidx.appcompat.app.AlertDialog.Builder(this)
                    builder.setTitle("Access denied")
                        .setMessage(
                            "You must have a coworking package selected before choosing a seat."
                        )
                        .setPositiveButton(
                            " Ok ", null
                        )
                    builder.create().show()
                }
            }

            else if(position == 2) {
                val i = Intent(this, OneTimeUseActivity::class.java)
                startActivity(i)
            }
            else if(position == 3) {
                val i = Intent(this, CreatedEventsActivity::class.java)
                startActivity(i)
            }
            else if(position == 4) {
                val i = Intent(this, PackagesActivity::class.java)
                startActivity(i)
            }


        }

        val scannerIcon = findViewById<ImageView>(R.id.qrIconImageView)

        scannerIcon.setImageResource(R.drawable.qr2)

        scannerIcon.setOnClickListener {

            val confirmScan = { _: DialogInterface, _: Int ->

                val intent = Intent(this, QrScanActivity::class.java)
                startActivity(intent)
                finish()
            }

            val builder = AlertDialog.Builder(this)
            builder.setTitle(" Confirmation ")
                .setMessage("The purpose is to scan your corresponding qr code to mark your presence in our coworking space. Are you sure? ")
                .setPositiveButton(" Yes ", DialogInterface.OnClickListener(function = confirmScan))
                .setNegativeButton(" No ", null)
            builder.create().show()
            
        }

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        val goToActivity = ActivityNavigation(bottomNav, this)

        goToActivity.navigate()

    }
}