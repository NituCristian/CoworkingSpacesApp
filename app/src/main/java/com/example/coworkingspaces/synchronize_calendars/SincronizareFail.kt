package com.example.coworkingspaces.synchronize_calendars

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
import androidx.fragment.app.FragmentTransaction
import com.example.coworkingspaces.R

class SincronizareFail() : Fragment(){



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_sincronizare_esec, container, false)

        val check = root.findViewById<ImageView>(R.id.sincronizare_fail_imageView)
        val success = root.findViewById<TextView>(R.id.sincronizare_fail_textView)
        val creating = root.findViewById<TextView>(R.id.textViewSincronizareFailWait)
        val progress = root.findViewById<ProgressBar>(R.id.progressBarSincronizareFail)
        val revenireButton = root.findViewById<Button>(R.id.revenerire_sincronizare_fail_button)
        check.visibility = View.GONE
        success.visibility = View.GONE
        revenireButton.visibility = View.GONE
//        creating.text = "Creating package \n\n  Please wait..."
//        success.text = "The package was successfully created!"

        val runable = Runnable {
            check.visibility = View.VISIBLE
            success.visibility = View.VISIBLE
            revenireButton.visibility = View.VISIBLE
            creating.visibility = View.GONE
            progress.visibility = View.GONE
        }

        revenireButton.setOnClickListener(View.OnClickListener {
            var fragment: Fragment = AlegereCalendar()


            replaceFragment(fragment)
        })



        Handler(Looper.getMainLooper()).postDelayed(runable, 3000)

        return root
    }

    private fun replaceFragment(someFragment: Fragment?) {
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        if (someFragment != null) {
            transaction.replace(R.id.nav_host_fragment22, someFragment)
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }
}