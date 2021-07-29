package com.example.coworkingspaces.create_coworking_packages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.coworkingspaces.R

class PackageActivity : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.activity_package, container, false)

        val addPackage = root.findViewById<ImageView>(R.id.imageViewaddPackage)
        val viewPackages = root.findViewById<ImageView>(R.id.imageViewViewPackages)
        addPackage.setOnClickListener(View.OnClickListener{
            var fragment: Fragment = PackageCreationDetailsFragment()
            replaceFragment(fragment)
        })
        viewPackages.setOnClickListener(View.OnClickListener{
//            var fragment: Fragment = PackageCreationDetailsFragment()
//            replaceFragment(fragment)
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