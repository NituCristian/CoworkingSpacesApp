package com.example.coworkingspaces.events

import android.app.Activity.RESULT_OK
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.content.ContentValues.TAG
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.bumptech.glide.Glide
import com.example.coworkingspaces.R
import java.util.*


class EventCreationDetailsActivity : Fragment(), AdapterView.OnItemSelectedListener  {

    private val RESULT_LOAD_IMAGE = 1
    lateinit var root : View
    var image : Boolean = false
    var setted : Boolean = false
    lateinit var venue : String
    var selectedImage : Uri? = null
    var hourS: Int = -1
    var minS: Int = -1
    var hourE: Int = -1
    var minE: Int = -1

    private lateinit var eventsAdapter: EventsAdapter;

    private var mDisplayDate: TextView? = null
    private var mDateSetListener: OnDateSetListener? = null
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.activity_event_creation_details, container, false)

        val buttonLoadImage: Button = root.findViewById(R.id.buttonLoadPicture) as Button
        val buttonCreateEvent: Button = root.findViewById(R.id.buttonCreateEvent) as Button

        val buttonDate: Button = root.findViewById(R.id.buttonEventDate) as Button
        val buttonStart: Button = root.findViewById(R.id.buttonEventStartTime) as Button
        val buttonEnd: Button = root.findViewById(R.id.buttonEventEndTime) as Button

        val start: TextView = root.findViewById(R.id.editTextStartTime)
        val end: TextView = root.findViewById(R.id.editTextEndTime)

        mDisplayDate = root.findViewById(R.id.editTextEventDate) as TextView

        buttonDate!!.setOnClickListener(View.OnClickListener {
            val cal: Calendar = Calendar.getInstance()
            val year: Int = cal.get(Calendar.YEAR)
            val month: Int = cal.get(Calendar.MONTH)
            val day: Int = cal.get(Calendar.DAY_OF_MONTH)
            val dialog = DatePickerDialog(
                    this.requireContext(),
                    mDateSetListener,
                    year, month, day)

            dialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
            dialog.show()
        })

        mDateSetListener = OnDateSetListener { datePicker, year, month, day ->
            var month = month
            month = month + 1
            Log.d(TAG, "onDateSet: mm/dd/yyy: $month/$day/$year")
            val date = "$day/$month/$year"
            mDisplayDate!!.setText(date)
            mDisplayDate!!.setTextColor(Color.BLACK)
        }

        buttonStart.setOnClickListener(View.OnClickListener {
            selectTimeS(start)
        })

        buttonEnd.setOnClickListener(View.OnClickListener {
            selectTimeE(end)
        })

        val spinner: Spinner = root.findViewById(R.id.spinner_venue)
        spinner.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
                this.requireContext(),
                R.array.rooms_for_events,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        buttonCreateEvent.setOnClickListener(View.OnClickListener {
            var allGood: Boolean = true
            val title: EditText = root.findViewById(R.id.editTextEventName)
            val details: EditText = root.findViewById(R.id.editTextEventDetails)

            if (title.getText().toString().trim().equals("")) {
                title.setError("Event name is required!");
                allGood = false
            }

            if (mDisplayDate!!.getText().toString().trim().equals("Date: 📅")) {
                mDisplayDate!!.setTextColor(Color.RED)
                allGood = false
            }

            if (start.getText().toString().trim().equals("Start Time: 🕑")) {
                start.setTextColor(Color.RED)
                allGood = false
            }
            if (end.getText().toString().trim().equals("End Time: 🕑")) {
                end.setTextColor(Color.RED)
                allGood = false
            }

            if (details.getText().toString().trim().equals("")) {
                details.setError("Event details are required!");
                allGood = false
            }
            if ((hourS>hourE || (hourS==hourE && minE<=minS))&&
                !start.getText().toString().trim().equals("Start Time: 🕑") &&
                !end.getText().toString().trim().equals("End Time: 🕑")){
                allGood = false

                val builder1 = AlertDialog.Builder(context)
                builder1.setTitle("Time selection error")
                builder1.setMessage("End time must be greater than start time!")
                builder1.setCancelable(true)

                builder1.setNegativeButton(
                    "Change time"
                ) { dialog, id -> dialog.cancel() }

                val alert11 = builder1.create()
                alert11.show()
            }
            if (!image){
                allGood = false
                val builder1 = AlertDialog.Builder(context)
                builder1.setMessage("You have to select an image for this event!")
                builder1.setCancelable(true)

                builder1.setNegativeButton(
                    "OK"
                ) { dialog, id -> dialog.cancel() }

                val alert11 = builder1.create()
                alert11.show()
            }

            if (allGood && image ) {
                var t: String = title.text.toString()
                var d: String = mDisplayDate!!.text.toString()
                var s: String = start.text.toString()
                var e: String = end.text.toString()
                var v: String = venue
                var de: String = details.text.toString()
                Event.name = t
                Event.date = d
                Event.start = s
                Event.end = e
                Event.venue = v
                Event.description = de
                Event.image = selectedImage

                val fragment: Fragment = EventCreatedFragment()
                replaceFragment(fragment)

                Event.dataSource.add(
                        Event_Card(
                        Event.name,
                        "\uD83D\uDCC6 " + Event.date + ", " + Event.start + "-" + Event.end,
                        "\uD83D\uDCCC" + Event.venue,
                        Event.description,
                        Event.image
                        )
                )
                //eventsAdapter.notifyDataSetChanged()


            }
        })

        buttonLoadImage.setOnClickListener(View.OnClickListener {
            val i = Intent(
                    Intent.ACTION_PICK,
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(i, RESULT_LOAD_IMAGE)
        })

        return root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            selectedImage = data.data
            val imageView: ImageView = root.findViewById(R.id.imageViewEvent) as ImageView
            Glide.with(this).load(selectedImage).into(imageView)
            image = true

        }
    }

    fun replaceFragment(someFragment: Fragment?) {
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        if (someFragment != null) {
            transaction.replace(R.id.nav_host_fragment, someFragment)
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun selectTimeS(time: TextView){
        val mcurrentTime: Calendar = Calendar.getInstance()
        val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
        val minute = mcurrentTime[Calendar.MINUTE]
        val mTimePicker: TimePickerDialog
        mTimePicker = TimePickerDialog(
                this.requireContext(),
                { timePicker, selectedHour, selectedMinute -> time.setText("$selectedHour:$selectedMinute"); hourS=selectedHour; minS = selectedMinute },
                hour, minute, true)
        time.setTextColor(Color.BLACK)
        mTimePicker.show()
    }

    fun selectTimeE(time: TextView){
        val mcurrentTime: Calendar = Calendar.getInstance()
        val hour = mcurrentTime[Calendar.HOUR_OF_DAY]
        val minute = mcurrentTime[Calendar.MINUTE]
        val mTimePicker: TimePickerDialog
        mTimePicker = TimePickerDialog(
                this.requireContext(),
                { timePicker, selectedHour, selectedMinute -> time.setText("$selectedHour:$selectedMinute"); hourE=selectedHour; minE = selectedMinute },
                hour, minute, true)
        time.setTextColor(Color.BLACK)
        mTimePicker.show()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            venue = parent.getItemAtPosition(position).toString()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        venue = ""
    }

}