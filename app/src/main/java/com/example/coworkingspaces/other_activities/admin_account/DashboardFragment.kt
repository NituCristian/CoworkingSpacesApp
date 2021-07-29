package com.example.coworkingspaces.other_activities.admin_account

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.coworkingspaces.other_activities.authentication.LoginActivity
import com.example.coworkingspaces.R

class DashboardFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         val root = inflater.inflate(R.layout.fragment_admin_account, container, false)

        val logout = root.findViewById<Button>(R.id.buttonAdminLogout);

        logout.setOnClickListener(View.OnClickListener{
            val intent = Intent(this.requireContext(), LoginActivity::class.java)
            startActivity(intent)
        })

        return root
    }
}