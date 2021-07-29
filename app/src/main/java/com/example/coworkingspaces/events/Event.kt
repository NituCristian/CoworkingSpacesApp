package com.example.coworkingspaces.events

import android.net.Uri

object Event{
    var name: String = ""
    var date: String = ""
    var start: String = ""
    var end: String = ""
    var venue: String = ""
    var description: String = ""
    var image: Uri? = null
    var dataSource:  ArrayList<Event_Card> = ArrayList()
}


//class Event(val name: String, val date: String, val start: String, val end String, val venue, String, val) {
//}