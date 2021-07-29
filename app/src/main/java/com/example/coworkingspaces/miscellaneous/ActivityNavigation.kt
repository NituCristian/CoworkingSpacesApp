package com.example.coworkingspaces.miscellaneous

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import com.example.coworkingspaces.synchronize_calendars.CalendarActivity
import com.example.coworkingspaces.other_activities.user_account.MyAccountActivity
import com.example.coworkingspaces.R
import com.example.coworkingspaces.other_activities.user_main_activity.UserHomePageActivity
import com.example.coworkingspaces.communication_channels.ChannelSelected
import com.example.coworkingspaces.communication_channels.ChatWithOthersActivity
import com.example.coworkingspaces.communication_channels.JoinAChatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ActivityNavigation(val bottomNavigationView: BottomNavigationView, val context: Context) {
    fun navigate() {
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    val intent = Intent(context, UserHomePageActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                    true
                }
                R.id.navigation_calendar -> {
                    val intent = Intent(context, CalendarActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                    true
                }
                R.id.navigation_dashboard -> {
                    val intent = Intent(context, MyAccountActivity::class.java)
                    ContextCompat.startActivity(context, intent, null)
                    //bottomNavigationView.selectedItemId = R.id.navigation_dashboard;

                    true
                }
                R.id.navigation_notifications -> {
                    if(!ChannelSelected.chosen){
                        val intent = Intent(context, JoinAChatActivity::class.java)
                        ContextCompat.startActivity(context, intent, null)}
                    else{
                        val intent = Intent(context, ChatWithOthersActivity::class.java)
                        ContextCompat.startActivity(context, intent, null)
                    }
                    true

                }
                else -> true
            }
        }
    }
}