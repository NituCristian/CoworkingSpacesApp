package com.example.coworkingspaces.other_activities.admin_main_activity


//class HomeFragment : Fragment() {
//
//  private lateinit var homeViewModel: HomeViewModel
//
//  override fun onCreateView(
//    inflater: LayoutInflater,
//    container: ViewGroup?,
//    savedInstanceState: Bundle?
//  ): View? {
//    homeViewModel =
//            ViewModelProvider(this).get(HomeViewModel::class.java)
//    val root = inflater.inflate(R.layout.fragment_home, container, false)
//
//    var asistentButton = root.findViewById<Button>(R.id.buttonAsistentVocal)
//    asistentButton.setOnClickListener{
//
//      val intent = Intent(root.context, Asistent_Vocal_Settings::class.java)
//      startActivity(intent)
//    }
//
//    val listaSali = root.findViewById<Button>(R.id.buttonSali)
//    listaSali.setOnClickListener(View.OnClickListener{
//      Navigation.findNavController(root).navigate(R.id.navigation_listaSali)
////      var fragment: Fragment = ListaSali()
////      replaceFragment(fragment)
//    })
//
//
//    val textView: TextView = root.findViewById(R.id.text_home)
//    homeViewModel.text.observe(viewLifecycleOwner, Observer {
//      textView.text = it
//    })
//
//
//
//    return root
//  }
//

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.coworkingspaces.R
import com.example.coworkingspaces.events.EventActivity
import com.example.coworkingspaces.create_coworking_packages.PackageActivity


class HomeFragment : Fragment() {

    private val dataSource = ArrayList<HomeCard>()
    private lateinit var cardsAdapter: HomeAdapter;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)


        val cardsListView = root.findViewById<ListView>(R.id.homeList)
        PopulateList.getCards(dataSource)
        cardsAdapter = HomeAdapter(this.requireContext(), dataSource)
        cardsListView.adapter = cardsAdapter

        cardsListView.setOnItemClickListener { parent, view, position, id ->
            if (position == 1) {
                var fragment: Fragment = EventActivity()
                replaceFragment(fragment)
            }
            if (position == 0) {
                var fragment: Fragment = PackageActivity()
                replaceFragment(fragment)
            }

        }
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