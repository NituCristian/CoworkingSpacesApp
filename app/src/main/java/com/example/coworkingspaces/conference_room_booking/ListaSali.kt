package com.example.coworkingspaces.conference_room_booking

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.coworkingspaces.R

class ListaSali: Fragment(){

    protected lateinit var backPressedCallback: OnBackPressedCallback
    private val dataSource = ArrayList<SalaCard>()
    private lateinit var saliAdapter: SalaAdapter;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {



        val root = inflater.inflate(R.layout.fragment_lista_sali, container, false)

        val saliListView = root.findViewById<ListView>(R.id.listaSali_listView)
        PopulateList.getCards(dataSource)
        saliAdapter = SalaAdapter(this.requireContext(), dataSource)
        saliListView.adapter = saliAdapter
        println("Inainte!")

//        saliListView.setOnItemClickListener { parent, view, position, id ->
//            println("Here! $position"
//
//            var fragment: Fragment = DetaliiSala()
//            var b: Bundle = Bundle()
//            b.putSerializable("sala", dataSource[position])
//            fragment.arguments = b
//            replaceFragment(fragment)
//        }

        saliListView.setOnItemClickListener { parent, view, position, id ->
            println("Here! $position")

            var fragment: Fragment = DetaliiSala()
            var b: Bundle = Bundle()
            b.putSerializable("sala", dataSource[position])
            b.putInt("Position", position);
            fragment.arguments = b
          //  Navigation.findNavController(root).navigate(R.id.navigation_detaliiSala, b)
            replaceFragment(fragment)
            }

            // backPressedCallback = requireActivity().onBackPressedDispatcher.addCallback(this) {
           // Navigation.findNavController(root).navigate(R.id.navigation_home)
         //   var fragment: Fragment = DetaliiSala()
        //    replaceFragment(fragment)
        //}

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