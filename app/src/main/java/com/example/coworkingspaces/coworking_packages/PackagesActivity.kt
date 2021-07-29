package com.example.coworkingspaces.coworking_packages

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.coworkingspaces.R
import com.example.coworkingspaces.miscellaneous.ActivityNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class PackagesActivity : AppCompatActivity() {
    private var coworkingPackages = ArrayList<CoworkingPackage>()
    private lateinit var coworkingPackagesViewAdapter: CoworkingPackagesViewAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coworking_packages)


        PopulateCoworkingPackages.populate()
        coworkingPackages = PopulateCoworkingPackages.coworkingPackages


        recyclerView = findViewById(R.id.coworking_packages_recycler_view)
        coworkingPackagesViewAdapter = CoworkingPackagesViewAdapter(this, coworkingPackages)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = coworkingPackagesViewAdapter

        bottomNavigationView = findViewById(R.id.bottomNav)
        val activityNavigation = ActivityNavigation(bottomNavigationView, this)
        activityNavigation.navigate()
    }
}