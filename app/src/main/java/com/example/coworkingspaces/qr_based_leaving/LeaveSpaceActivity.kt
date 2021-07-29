package com.example.coworkingspaces.qr_based_leaving

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import com.example.coworkingspaces.R

class LeaveSpaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_leave_space)

        val qrImage = findViewById<ImageView>(R.id.scanImageView)

        qrImage.setImageResource(R.drawable.qr2)

        val runnable = Runnable {
            val intent = Intent(this, LeaveSpaceSuccessful::class.java )
            startActivity(intent)
        }


        Handler(Looper.getMainLooper()).postDelayed(runnable, 3000);
    }
}