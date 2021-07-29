package com.example.coworkingspaces.synchronize_calendars

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coworkingspaces.R
import com.example.coworkingspaces.miscellaneous.ActivityNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_sali2)

        val bottomNavigationView =findViewById<BottomNavigationView>(R.id.bottomNav)

        bottomNavigationView.selectedItemId = R.id.navigation_calendar;

        val goToActivity = ActivityNavigation(bottomNavigationView, this)

        goToActivity.navigate()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.nav_host_fragment22, Calendar()).commit()
        }

    }
}