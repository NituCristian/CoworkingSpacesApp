package com.example.coworkingspaces.qr_based_access

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.coworkingspaces.R
import com.example.coworkingspaces.other_activities.user_main_activity.UserHomePageActivity
import com.example.coworkingspaces.miscellaneous.ActivityNavigation
import com.example.coworkingspaces.other_activities.user_account.UserInfoSingleton
import com.google.android.material.bottomnavigation.BottomNavigationView

class QrScanSuccessfulActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qr_scan_successful)

        val backToMainButton = findViewById<Button>(R.id.qrBackToMainButton)

        val name = findViewById<TextView>(R.id.nameTextView)

        name.text = UserInfoSingleton.name

        backToMainButton.setOnClickListener {
            val intent = Intent(this@QrScanSuccessfulActivity, UserHomePageActivity::class.java )
            startActivity(intent)
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNav)

        val goToActivity = ActivityNavigation(bottomNavigationView, this)

        goToActivity.navigate()

    }
}