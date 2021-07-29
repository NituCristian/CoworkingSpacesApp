package com.example.coworkingspaces.conference_room_booking

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coworkingspaces.R
import com.example.coworkingspaces.miscellaneous.ActivityNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class ListaSaliActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_sali2)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        val goToActivity = ActivityNavigation(bottomNav, this)

        goToActivity.navigate()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.nav_host_fragment22, ListaSali()).commit()
        }



    }
}