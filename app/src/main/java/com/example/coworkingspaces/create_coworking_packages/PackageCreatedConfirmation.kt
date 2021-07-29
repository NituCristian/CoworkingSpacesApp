package com.example.coworkingspaces.create_coworking_packages

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.coworkingspaces.other_activities.admin_main_activity.AdminHomePageActivity
import com.example.coworkingspaces.R

class PackageCreatedConfirmation: Fragment() {

    lateinit var root: View

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_event_created, container, false)

        val check = root.findViewById<ImageView>(R.id.imageViewCheck)
        val success = root.findViewById<TextView>(R.id.textViewSucces)
        val creating = root.findViewById<TextView>(R.id.textViewCreating)
        val viewButon = root.findViewById<Button>(R.id.buttonViewCreatedEvent)
        val progress = root.findViewById<ProgressBar>(R.id.progressBarCreating)
        check.visibility = View.GONE
        success.visibility = View.GONE
        viewButon.visibility = View.GONE
        creating.text = "Creating package \n\n  Please wait..."
        success.text = "The package was successfully created!"

        val runable = Runnable {
            check.visibility = View.VISIBLE
            success.visibility = View.VISIBLE
            viewButon.visibility = View.VISIBLE
            viewButon.setText("Go back to home page")
            creating.visibility = View.GONE
            progress.visibility = View.GONE
        }

        Handler(Looper.getMainLooper()).postDelayed(runable, 3000)

        viewButon.setOnClickListener(View.OnClickListener{

            val intent = Intent(this.requireContext(), AdminHomePageActivity::class.java)
            startActivity(intent)
        })
        return root

    }
}