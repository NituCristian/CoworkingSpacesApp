package com.example.coworkingspaces.other_activities.authentication

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.example.coworkingspaces.R

class RegisterActivity : AppCompatActivity() {


    lateinit var toolbar: ActionBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val registerTextView = findViewById<TextView>(R.id.singup)
        registerTextView.setOnClickListener(View.OnClickListener {
            val builder1 = AlertDialog.Builder(this)
            builder1.setTitle("Successful registration!")
            builder1.setMessage("Your new account has been successfully created!")
            builder1.setCancelable(true)

            builder1.setPositiveButton(
                "Login") { dialog, which ->
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }



            val alert11 = builder1.create()
            alert11.show()
        })

        val redirectTextView = findViewById<TextView>(R.id.redirect_login)
        redirectTextView.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        })




    }
}