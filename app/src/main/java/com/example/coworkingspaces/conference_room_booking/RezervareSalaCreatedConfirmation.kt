package com.example.coworkingspaces.conference_room_booking

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.coworkingspaces.R


class RezervareSalaCreatedConfirmation: Fragment() {

    lateinit var root: View

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_rezervare_sala_succes, container, false)

        val check = root.findViewById<ImageView>(R.id.imageViewCheckRezervare)
        val success = root.findViewById<TextView>(R.id.textViewSuccesRezervare)
        val creating = root.findViewById<TextView>(R.id.textViewCreatingRezervare)
        val progress = root.findViewById<ProgressBar>(R.id.progressBarCreatingRezervare)
        check.visibility = View.GONE
        success.visibility = View.GONE
//        creating.text = "Creating package \n\n  Please wait..."
//        success.text = "The package was successfully created!"

        val runable = Runnable {
            check.visibility = View.VISIBLE
            success.visibility = View.VISIBLE
            creating.visibility = View.GONE
            progress.visibility = View.GONE
        }

        Handler(Looper.getMainLooper()).postDelayed(runable, 3000)
        //activity?.finish()
        return root

    }
}