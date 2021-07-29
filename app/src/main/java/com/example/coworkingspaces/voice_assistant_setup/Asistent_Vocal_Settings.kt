package com.example.coworkingspaces.voice_assistant_setup

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import com.example.coworkingspaces.R
import com.example.coworkingspaces.other_activities.user_main_activity.UserHomePageActivity
import com.example.coworkingspaces.miscellaneous.ActivityNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView


class Asistent_Vocal_Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asistent__vocal__settings)

        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)

        val goToActivity = ActivityNavigation(bottomNav, this)

        goToActivity.navigate()


        var salveazaButton = findViewById<Button>(R.id.salveza_setari_button)

        salveazaButton.setOnClickListener{
            val builder = AlertDialog.Builder(this@Asistent_Vocal_Settings)
            builder.setTitle("Voice assistant settings have been updated!")
                    .setCancelable(false)
                    .setPositiveButton("OK") {
                        dialog, id ->
                        val intent = Intent(this, UserHomePageActivity::class.java)
                        startActivity(intent)
                    }
            val alert = builder.create()
            alert.show()
        }
        //here

        val spinnerGest: Spinner = findViewById(R.id.gest_activare_spinner)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.gest_activare,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerGest.adapter = adapter
        }

        val spinnerFraza: Spinner = findViewById(R.id.fraza_vocala_spinner)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                this,
            R.array.fraza_vocala_de_activare,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerFraza.adapter = adapter
        }

        val spinnerVoce: Spinner = findViewById(R.id.voce_asistent_spinner)
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
                this,
            R.array.vocea_asistentului,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinnerVoce.adapter = adapter
        }

        val sharedPref = this.getPreferences(Context.MODE_PRIVATE)
        val myEdit = sharedPref.edit()

        spinnerGest.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                myEdit.putInt("spinnerGest", position).commit()
            }

        }

        spinnerFraza.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                myEdit.putInt("spinnerFraza", position).commit()
            }

        }

        spinnerVoce.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

                myEdit.putInt("spinnerVoce", position).commit()
            }

        }


        spinnerGest.setSelection(sharedPref.getInt("spinnerGest", 0));
        spinnerFraza.setSelection(sharedPref.getInt("spinnerFraza", 0));
        spinnerVoce.setSelection(sharedPref.getInt("spinnerVoce", 0));
    }
}