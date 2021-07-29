package com.example.coworkingspaces.communication_channels

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.coworkingspaces.R
import com.example.coworkingspaces.miscellaneous.ActivityNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class JoinAChatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_a_chat)

        val button = findViewById<Button>(R.id.buttonJoinChannel)

        val bottomNavigationView =findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigationView.selectedItemId = R.id.navigation_notifications;

        val goToActivity = ActivityNavigation(bottomNavigationView, this)
        goToActivity.navigate()

        button.setOnClickListener(View.OnClickListener{
            val intent = Intent(this, ChannelsActivity::class.java)
            startActivity(intent)
        })
    }
}