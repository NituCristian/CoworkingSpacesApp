package com.example.coworkingspaces.synchronize_calendars

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.FragmentTransaction
import com.example.coworkingspaces.R


class AlegereCalendar : Fragment() {

    private var dataSource = ArrayList<AplicatieCalendar>()
    private lateinit var aplicatieCalendarAdapter: AplicatieCalendarAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val root =  inflater.inflate(R.layout.fragment_alegere_calendar, container, false)


//        var events = ArrayList<EventDay>()
//        if(arguments != null) {
//            events = arguments?.getSerializable("events") as ArrayList<EventDay>
//        }

        val aplicatiiListView = root.findViewById<ListView>(R.id.aplicatii_calendare_listView)
        PopulateAplicatii.getAplicatii(dataSource)
        aplicatieCalendarAdapter = AplicatieCalendarAdapter(this.requireContext(), dataSource)
        aplicatiiListView.adapter = aplicatieCalendarAdapter

        aplicatiiListView.setOnItemClickListener{parent, view, position, id ->
            val builder1 = AlertDialog.Builder(this.requireContext())
            builder1.setTitle("Synchronize with " + dataSource[position].aplicatie)
            builder1.setMessage("Are you sure that you want to synchronize the apps calendar with " + dataSource[position].aplicatie +"?")
                     builder1.setCancelable(true)

            builder1.setPositiveButton(
                "Yes") { dialog, which ->
                    if(position == 1 ){
                        var fragment: Fragment = SincronizareFail()
//                        var b: Bundle = Bundle()
//                        b.putSerializable("events", events)
//                        fragment.arguments = b

                        replaceFragment(fragment)
                    }else {
                        var fragment: Fragment = SincronizareSucces()
//                        var b: Bundle = Bundle()
//                        b.putSerializable("events", events)
//                        fragment.arguments = b

                        replaceFragment(fragment)

                    }
//                val intent = Intent(this, ChatWithOthersActivity::class.java)
//                intent.putExtra("chatImage", dataSource[position].image)
//                intent.putExtra("chatChannel", dataSource[position].channel)
//                startActivity(intent)

            }

            builder1.setNegativeButton(
                "No"
            ) { dialog, id -> dialog.cancel() }

            val alert11 = builder1.create()
            alert11.show()
        }

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
