package com.example.coworkingspaces.conference_room_booking

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.airbnb.lottie.LottieAnimationView
import com.example.coworkingspaces.R
import com.example.coworkingspaces.choose_seat.ChooseSeatActivity
import java.util.*


class DetaliiSala : Fragment(){

    private lateinit var btnDatePicker: Button
    private lateinit var btnStartTimePicker:Button
    private lateinit var btnEndTimePicker:Button
    private lateinit var txtDate: EditText
    private lateinit var txtStartTime:EditText
    private lateinit var txtEndTime:EditText
    private lateinit var animation: LottieAnimationView
    private var mYear = 0
    private var mMonth:Int = 0
    private var mDay:Int = 0
    private var mHour:Int = 0
    private var mMinute:Int = 0
    private var correctDate = false
    private var correctStartTime = false
    private var correctEndTime = false
    private var startHours = 0
    private var startminutes = 0

    private var price = 0.0
    private var salaPosition = 0

    private lateinit var sala: SalaCard;
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_sala, container, false)

        if(arguments != null) {
            sala = arguments?.getSerializable("sala") as SalaCard
            salaPosition = arguments?.getInt("Position")!!
        }

        val imageView = root.findViewById<ImageView>(R.id.sala_imageView)
        val textView = root.findViewById<TextView>(R.id.nume_sala_text_view)
        val textViewDescription = root.findViewById<TextView>(R.id.detailed_sala_description_text_view)
        val rezervaButton = root.findViewById<Button>(R.id.rezerva_sala_button)
        val priceTextView = root.findViewById<TextView>(R.id.sala_price_text_view)
        val checkBox1 = root.findViewById<CheckBox>(R.id.sala_checkbox_1)
        val checkBox2 = root.findViewById<CheckBox>(R.id.sala_checkbox_2)
        val checkBox3 = root.findViewById<CheckBox>(R.id.sala_checkbox_3)
        animation = root.findViewById<LottieAnimationView>(R.id.sala_animation)
        animation.visibility = View.GONE

        imageView.setImageDrawable(this.requireContext().getDrawable(sala.imageId))
        textView.text = sala.title
        textViewDescription.text = sala.description
        price = sala.price.toDouble()
        priceTextView.text = sala.price.toString()


        btnDatePicker=root.findViewById<Button>(R.id.btn_date);
        btnStartTimePicker=root.findViewById<Button>(R.id.btn_start_time);
        btnEndTimePicker=root.findViewById<Button>(R.id.btn_end_time);
        txtDate=root.findViewById<EditText>(R.id.in_date);
        txtDate.isEnabled = false
        txtStartTime=root.findViewById<EditText>(R.id.in_start_time);
        txtStartTime.isEnabled = false
        txtEndTime=root.findViewById<EditText>(R.id.in_end_time);
        txtEndTime.isEnabled = false

        btnDatePicker.setOnClickListener(View.OnClickListener {
// Get Current Date

            // Get Current Date
            val c: Calendar = Calendar.getInstance()
            mYear = c.get(Calendar.YEAR)
            mMonth = c.get(Calendar.MONTH)
            mDay = c.get(Calendar.DAY_OF_MONTH)


            val datePickerDialog = DatePickerDialog(
                this.requireContext(),
                OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    if(validateDate(year, monthOfYear, dayOfMonth)){
                        txtDate.setText(
                            dayOfMonth.toString() + "-" + (monthOfYear + 1) + "-" + year
                        )
                        correctDate = true
                    }
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        })

        btnStartTimePicker.setOnClickListener(View.OnClickListener {
            // Get Current Time

            // Get Current Time
            val c = Calendar.getInstance()
            mHour = c[Calendar.HOUR_OF_DAY]
            mMinute = c[Calendar.MINUTE]

            // Launch Time Picker Dialog

            // Launch Time Picker Dialog
            val timePickerDialog = TimePickerDialog(
                this.requireContext(),
                OnTimeSetListener { view, hourOfDay, minute ->

                    correctStartTime = hourOfDay in 8..22
                    startHours = hourOfDay
                    startminutes = minute
                    if(minute < 10)
                        txtStartTime.setText("$hourOfDay:0$minute")
                    else
                        txtStartTime.setText("$hourOfDay:$minute")},
                mHour,
                mMinute,
                true
            )
            timePickerDialog.show()
        });

        btnEndTimePicker.setOnClickListener(View.OnClickListener {
            // Get Current Time

            // Get Current Time
            val c = Calendar.getInstance()
            mHour = c[Calendar.HOUR_OF_DAY]
            mMinute = c[Calendar.MINUTE]

            // Launch Time Picker Dialog

            // Launch Time Picker Dialog
            val timePickerDialog = TimePickerDialog(
                this.requireContext(),
                OnTimeSetListener { view, hourOfDay, minute ->
                    if(hourOfDay>7 && hourOfDay<23 && (hourOfDay>startHours || (hourOfDay == startHours && minute>startminutes))){
                        correctEndTime = true
                    }else{
                        correctEndTime = false
                    }

                    if(minute < 10)
                        txtEndTime.setText("$hourOfDay:0$minute")
                    else
                        txtEndTime.setText("$hourOfDay:$minute") },
                mHour,
                mMinute,
                true
            )
            timePickerDialog.show()
        });

        checkBox1.setOnClickListener(View.OnClickListener {

            if (checkBox1.isChecked) {
                price += 20
            } else {
                price -= 20
            }; priceTextView.text = "$price lei"
        })

        checkBox2.setOnClickListener(View.OnClickListener {

            if (checkBox2.isChecked) {
                price += 30
            } else {
                price -= 30
            }; priceTextView.text = "$price lei"
        })

        checkBox3.setOnClickListener(View.OnClickListener {

            if (checkBox3.isChecked) {
                price += 40
            } else {
                price -= 40
            }; priceTextView.text = "$price lei"
        })

        rezervaButton.setOnClickListener(View.OnClickListener {
//            if(correctDate&& correctEndTime && correctStartTime)
//                Navigation.findNavController(root).navigate(R.id.navigation_rezervareSalaCreatedConfirmation)
//            else{
//
//            }

            if (!correctDate || !correctStartTime || !correctEndTime) {
                val builder = AlertDialog.Builder(this.requireContext())
                builder.setTitle("The reservation has failed")
                    .setMessage(
                        "The date or the time interval are invalid"
                    )
                    .setPositiveButton(
                        " Ok ", null
                    )
                builder.create().show()
            } else {
                val builder = AlertDialog.Builder(this.requireContext())
                builder.setTitle("Confirm payment")
                    .setMessage(
                        "\nThe conference room has been booked for\n" +
                                txtDate.text+"\n"+
                                "in the time interval"+"\n"+
                                txtStartTime.text+ " - "+ txtEndTime.text+"\n"+
                                "\nPrice to pay $price lei."
                    )
                    .setPositiveButton(
                        " Confirm "
                    ) { _: DialogInterface, _: Int ->
                        setSelected(salaPosition);
                        showConfirmationAnimation()
                    }
                    .setNegativeButton(
                        " Cancel ", null
                    )
                builder.create().show()
            }
        })

        return root
    }

    private val showConfirmationAnimation = {
        animation.visibility = View.VISIBLE
        animation.playAnimation()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                animation.visibility = View.GONE
                var fragment: Fragment = ListaSali();
                //  Navigation.findNavController(root).navigate(R.id.navigation_detaliiSala, b)
                replaceFragment(fragment)
            },
            2200
        )
    }

    private fun setSelected(position: Int) {

        PopulateList.selectionStatus[position] = true
    }

    fun validateDate(year:Int, monthOfYear:Int, dayOfMonth:Int):Boolean{
        val curDate = Calendar.getInstance()
        val date = Calendar.getInstance()
        date.set(year, monthOfYear, dayOfMonth)
        return curDate <= date
    }

    fun replaceFragment(someFragment: Fragment?) {
        val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
        if (someFragment != null) {
            transaction.replace(R.id.nav_host_fragment22, someFragment)
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }
}