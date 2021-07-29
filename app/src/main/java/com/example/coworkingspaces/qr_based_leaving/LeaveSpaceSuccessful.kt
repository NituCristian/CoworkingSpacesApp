package com.example.coworkingspaces.qr_based_leaving

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.coworkingspaces.other_activities.authentication.LoginActivity
import com.example.coworkingspaces.R
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class LeaveSpaceSuccessful : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leave_space_successful)

        val backToMainButton = findViewById<Button>(R.id.qrLogOut)
        val time = findViewById<TextView>(R.id.timeTextView)

            time.text = "Successfully logged out at:"
            time.text = "" + time.text + '\n' + "      " +
                        DateTimeFormatter
                            .ofPattern("yyyy-MM-dd HH:mm:ss")
                            .withZone(ZoneId.of("Europe/Bucharest"))
                            .format(Instant.now())

        backToMainButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java )
            startActivity(intent)
        }
    }
}