package com.example.coworkingspaces.communication_channels

import com.example.coworkingspaces.R

class PopulateChannelsList {

    companion object Factory {

        fun getChannels(dataSource: ArrayList<Channel>) {
            dataSource.clear()
            dataSource.add(
                Channel(
                    R.drawable.slack,
                    "Slack channel"
                )
            )
            dataSource.add(
                Channel(
                    R.drawable.skype,
                    "Skype channel"
                )
            )
            dataSource.add(
                Channel(
                    R.drawable.whatsapp,
                    "WhatsApp channel"
                )
            )
            dataSource.add(
                Channel(
                    R.drawable.messenger,
                    "Messenger channel"
                )
            )
        }
    }
}