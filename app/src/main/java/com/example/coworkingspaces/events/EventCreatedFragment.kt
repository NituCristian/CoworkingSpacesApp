package com.example.coworkingspaces.events

import android.media.Image
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.example.coworkingspaces.R


class EventCreatedFragment : Fragment() {

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

        val runable = Runnable {
            check.visibility = View.VISIBLE
            success.visibility = View.VISIBLE
            viewButon.visibility = View.VISIBLE
            creating.visibility = View.GONE
            progress.visibility = View.GONE
        }

        Handler(Looper.getMainLooper()).postDelayed(runable, 3000)

        viewButon.setOnClickListener(View.OnClickListener{

            var fragment: Fragment = EventDetailsFragment()
            replaceFragment(fragment)

        })
        return root

    }

    fun replaceFragment(someFragment: Fragment?) {
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        if (someFragment != null) {
            transaction.replace(R.id.nav_host_fragment, someFragment)
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }


}