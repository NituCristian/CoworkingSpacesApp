package com.example.coworkingspaces.communication_channels

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.coworkingspaces.R
import com.example.coworkingspaces.miscellaneous.ActivityNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class ChannelsActivity: AppCompatActivity() {

    private lateinit var channelsAdapter: ChannelAdapter
    private val dataSource = ArrayList<Channel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choose_channel)

        val channelsListView = findViewById<ListView>(R.id.listView_Channels)
        PopulateChannelsList.getChannels(dataSource)
        channelsAdapter = ChannelAdapter(this, dataSource)
        channelsListView.adapter = channelsAdapter

        val bottomNavigationView =findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigationView.selectedItemId = R.id.navigation_notifications;

        val goToActivity = ActivityNavigation(bottomNavigationView, this)

        goToActivity.navigate()

        channelsListView.setOnItemClickListener { parent, view, position, id ->

            val builder1 = AlertDialog.Builder(this)
            builder1.setTitle("Joining " + dataSource[position].channel)
            builder1.setMessage("Are you sure you want to join " + dataSource[position].channel +
                    "?\n\nYou will be able to chat with others both using this app and using the selected channel.")
            builder1.setCancelable(true)

            builder1.setPositiveButton(
                "Yes") { dialog, which ->

                val intent = Intent(this, ChatWithOthersActivity::class.java)
                intent.putExtra("chatImage", dataSource[position].image)
                intent.putExtra("chatChannel", dataSource[position].channel)
                startActivity(intent)
                ChannelSelected.image = dataSource[position].image
                ChannelSelected.channel= dataSource[position].channel


            }

            builder1.setNegativeButton(
                "Choose another"
            ) { dialog, id -> dialog.cancel() }

            val alert11 = builder1.create()
            alert11.show()

        }

    }
}