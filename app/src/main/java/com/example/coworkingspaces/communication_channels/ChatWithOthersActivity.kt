package com.example.coworkingspaces.communication_channels

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.coworkingspaces.R
import com.example.coworkingspaces.miscellaneous.ActivityNavigation
import com.google.android.material.bottomnavigation.BottomNavigationView

class ChatWithOthersActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.chat_with_others_activity)

        val chatImageView= findViewById<ImageView>(R.id.imageViewChatWithOthers)
//        val image = intent.getIntExtra("chatImage", 0)
//        val channel = intent.getStringExtra("chatChannel")
        val image = ChannelSelected.image
        val channel = ChannelSelected.channel
        val chats = findViewById<TextView>(R.id.textViewChats)
        val chatName = findViewById<TextView>(R.id.textViewChatName)
        val chatPeople = findViewById<TextView>(R.id.textViewChatPeople)
        val chatWait = findViewById<TextView>(R.id.textViewChatWait)
        val slack = findViewById<TextView>(R.id.textViewSlackChannel)
        val progress = findViewById<ProgressBar>(R.id.progressBarChat)

        val bottomNavigationView =findViewById<BottomNavigationView>(R.id.bottomNav)
        bottomNavigationView.selectedItemId = R.id.navigation_notifications;

        val goToActivity = ActivityNavigation(bottomNavigationView, this)

        goToActivity.navigate()
        if(!ChannelSelected.chosen) {
            if (channel != null) {
                chatWait.text = "Connecting with " + channel.substring(
                    0,
                    channel.length - 7
                ) + "\n\n\nPlease wait..."
            }
            chats.visibility = View.GONE
            chatName.visibility = View.GONE
            chatImageView.visibility = View.GONE
            chatPeople.visibility = View.GONE
            slack.visibility = View.GONE


            val runable = Runnable {
                chatWait.visibility = View.GONE
                progress.visibility = View.GONE
                if(channel.substring(0, channel.length - 7) =="Slack ")
                     slack.visibility = View.VISIBLE
                chats.visibility = View.VISIBLE
                chatName.visibility = View.VISIBLE
                chatImageView.visibility = View.VISIBLE
                chatPeople.visibility = View.VISIBLE
            }

            Handler(Looper.getMainLooper()).postDelayed(runable, 4000)
            chatImageView.setImageDrawable(getDrawable(image))
            ChannelSelected.chosen = true
        }else{
            if(channel.substring(0, channel.length - 7) =="Slack ")
                slack.visibility = View.VISIBLE
            else{
                slack.visibility = View.GONE
            }
            chatWait.visibility = View.GONE
            progress.visibility = View.GONE
            chats.visibility = View.VISIBLE
            chatName.visibility = View.VISIBLE
            chatImageView.visibility = View.VISIBLE
            chatPeople.visibility = View.VISIBLE
            chatImageView.setImageDrawable(getDrawable(image))
        }
    }
}