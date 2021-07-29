package com.example.coworkingspaces.coworking_packages

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.airbnb.lottie.LottieAnimationView
import com.example.coworkingspaces.R
import com.example.coworkingspaces.miscellaneous.ActivityNavigation
import com.example.coworkingspaces.choose_seat.ChooseSeatActivity
import com.example.coworkingspaces.other_activities.user_account.PopulateUsers
import com.example.coworkingspaces.other_activities.user_account.User
import com.example.coworkingspaces.other_activities.user_account.UserInfoSingleton
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.collections.ArrayList

class DetailedPackageActivity : AppCompatActivity(), View.OnClickListener,
    DatePickerDialog.OnDateSetListener {
    private var coworkingPackageposition: Int = 0
    private lateinit var coworkingPackage: CoworkingPackage

    private lateinit var imageView: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var featuresTextView: TextView

    private lateinit var optionOneCheckBox: CheckBox
    private lateinit var optionTwoCheckBox: CheckBox
    private lateinit var optionThreeCheckBox: CheckBox

    private lateinit var selectedInitialDateTextView: TextView
    private lateinit var selectInitialDateButton: Button
    private var initialDate = true
    private var currentDay = 0
    private var currentMonth = 0
    private var currentYear = 0

    private var selectedInitialDay = 0
    private var selectedInitialMonth = 0
    private var selectedInitialYear = 0

    private lateinit var selectedFinalDateTextView: TextView
    private lateinit var selectFinalDateButton: Button

    private var selectedFinalDay = 0
    private var selectedFinalMonth = 0
    private var selectedFinalYear = 0

    private var dateInitial = LocalDate.of(2050, 12, 31)
    private var daysBetween = 0L

    private lateinit var selectPackageButton: Button
    private lateinit var priceTextView: TextView
    private var price = 0.0f

    private lateinit var animation: LottieAnimationView
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_package)

        imageView = findViewById(R.id.detailed_coworking_package_image_view)
        titleTextView = findViewById(R.id.detailed_coworking_package_title_text_view)
        descriptionTextView = findViewById(R.id.detailed_coworking_package_description_text_view)
        featuresTextView = findViewById(R.id.detailed_coworking_package_features_text_view)

        optionOneCheckBox = findViewById(R.id.coworking_package_checkbox_1)
        optionOneCheckBox.setOnClickListener(this)
        optionTwoCheckBox = findViewById(R.id.coworking_package_checkbox_2)
        optionTwoCheckBox.setOnClickListener(this)
        optionThreeCheckBox = findViewById(R.id.coworking_package_checkbox_3)
        optionThreeCheckBox.setOnClickListener(this)

        selectedInitialDateTextView = findViewById(R.id.selected_initial_date_text_view)
        selectInitialDateButton = findViewById(R.id.select_initial_date_button)
        selectInitialDateButton.setOnClickListener(this)

        selectedFinalDateTextView = findViewById(R.id.selected_final_date_text_view)
        selectFinalDateButton = findViewById(R.id.select_final_date_button)
        selectFinalDateButton.setOnClickListener(this)

        selectPackageButton = findViewById(R.id.select_package_button)
        selectPackageButton.setOnClickListener(this)

        priceTextView = findViewById(R.id.coworking_package_price_text_view)

        animation = findViewById(R.id.coworking_package_animation)
        animation.visibility = View.GONE

        coworkingPackageposition = intent.getIntExtra("packagePosition", 0)
        coworkingPackage = (intent.getSerializableExtra("coworkingPackage") as? CoworkingPackage)!!

        setupActivityInfo()

        bottomNavigationView = findViewById(R.id.bottomNav)
        val activityNavigation = ActivityNavigation(bottomNavigationView, this)
        activityNavigation.navigate()
    }

    @SuppressLint("SetTextI18n", "UseCompatLoadingForDrawables")
    private fun setupActivityInfo() {
        imageView.setImageDrawable(this.getDrawable(coworkingPackage.imageId))
        titleTextView.text = coworkingPackage.title
        descriptionTextView.text = coworkingPackage.description
        price = coworkingPackage.price
        priceTextView.text = coworkingPackage.price.toString() + " lei"
        var features = ""
        for (feature in coworkingPackage.features) {
            features += "\u25CF "
            features += feature
            features += "\n"
        }
        features = features.subSequence(0, features.length - 1) as String
        featuresTextView.text = features
    }

    private fun setSelected(position: Int) {

        var statusList = ArrayList<Boolean>()

        var size = 10

        while (size > 0) {
            statusList.add(false)
            size--
        }

        statusList[position] = true
        PopulateCoworkingPackages.selectionStatus = statusList


        PopulateCoworkingPackages.modifyStatus()
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.coworking_package_checkbox_1 -> {
                    if (optionOneCheckBox.isChecked) {
                        price += (60 * daysBetween.toFloat() / 30.0f).toInt().toFloat()
                    } else {
                        price -= (60 * daysBetween.toFloat() / 30.0f).toInt().toFloat()
                    }; priceTextView.text = "$price lei"
                }
                R.id.coworking_package_checkbox_2 -> {
                    if (optionTwoCheckBox.isChecked) {
                        price += (70 * daysBetween.toFloat() / 30.0f).toInt().toFloat()
                    } else {
                        price -= (70 * daysBetween.toFloat() / 30.0f).toInt().toFloat()
                    }; priceTextView.text = "$price lei"
                }
                R.id.coworking_package_checkbox_3 -> {
                    if (optionThreeCheckBox.isChecked) {
                        price += (80 * daysBetween.toFloat() / 30.0f).toInt().toFloat()
                    } else {
                        price -= (80 * daysBetween.toFloat() / 30.0f).toInt().toFloat()
                    }; priceTextView.text = "$price lei"
                }

                R.id.select_initial_date_button -> {
                    getDateCalendar()
                    initialDate = true
                    DatePickerDialog(
                        this,
                        this,
                        currentYear,
                        currentMonth,
                        currentDay
                    ).show()
                }

                R.id.select_final_date_button -> {
                    getDateCalendar()
                    initialDate = false
                    DatePickerDialog(
                        this,
                        this,
                        currentYear,
                        currentMonth,
                        currentDay
                    ).show()
                }

                R.id.select_package_button -> showConfirmationDialog()
            }
        }
    }

    private val showConfirmationAnimation = {
        animation.visibility = View.VISIBLE
        animation.playAnimation()
        Handler(Looper.getMainLooper()).postDelayed(
            {
                animation.visibility = View.GONE
                val intent = Intent(this, ChooseSeatActivity::class.java)
                ContextCompat.startActivity(this, intent, null)
            },
            2200
        )
    }

    private fun showConfirmationDialog() {
        if (selectedInitialYear == 0 || selectedFinalYear == 0) {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Check-out failed")
                .setMessage(
                    "Both initial and final date must be selected."
                )
                .setPositiveButton(
                    " Ok ", null
                )
            builder.create().show()
        } else {
            var optionsSelected = ""
            if (optionOneCheckBox.isChecked) {
                optionsSelected += "\n\u25CF "
                optionsSelected += optionOneCheckBox.text
            }
            if (optionTwoCheckBox.isChecked) {
                optionsSelected += "\n\u25CF "
                optionsSelected += optionTwoCheckBox.text
            }
            if (optionThreeCheckBox.isChecked) {
                optionsSelected += "\n\u25CF "
                optionsSelected += optionThreeCheckBox.text
            }

            var options = ""
            if (optionsSelected.isEmpty())
                options = "No options selected."
            else
                options = "Selected options: $optionsSelected"

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Confirm payment")
                .setMessage(
                    "\nPackage selected for interval\n" +
                            "$selectedInitialDay/$selectedInitialMonth/$selectedInitialYear - " +
                            "$selectedFinalDay/$selectedFinalMonth/$selectedFinalYear.\n" +
                            options +
                            "\nYou have to pay $price lei."
                )
                .setPositiveButton(
                    " Confirm "
                ) { _: DialogInterface, _: Int ->
                    setSelected(coworkingPackageposition);
                    showConfirmationAnimation()
                    var userIndex = PopulateUsers.getUserIndex(UserInfoSingleton.username, UserInfoSingleton.password)
                    PopulateUsers.users[userIndex].packageSelected = true
                    ChosenReservation.chosen = true
                    ChosenReservation.reservationType = "package"
                    ChosenReservation.packageName = titleTextView.text as String
                    ChosenReservation.extraOptionsPackage = getSelectedOptions()
                    ChosenReservation.initialDate =
                        "$selectedInitialDay/$selectedInitialMonth/$selectedInitialYear"
                    ChosenReservation.finalDate =
                        "$selectedFinalDay/$selectedFinalMonth/$selectedFinalYear"
                    ChosenReservation.isExistentPackage = false;
                    ChosenReservation.putSelectedOnFalse = false;
                }
                .setNegativeButton(
                    " Cancel ", null
                )
            builder.create().show()
        }
    }

    private fun getDateCalendar() {
        if (currentYear == 0) {
            val calendar = Calendar.getInstance()
            currentDay = calendar.get(Calendar.DAY_OF_MONTH)
            currentMonth = calendar.get(Calendar.MONTH)
            currentYear = calendar.get(Calendar.YEAR)
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        if (LocalDate.of(year, (month + 1), dayOfMonth)
                .isEqual(LocalDate.now()) || (LocalDate.of(year, (month + 1), dayOfMonth)
                .isAfter(LocalDate.now()))
        ) {
            if (initialDate) {
                selectedInitialDay = dayOfMonth
                selectedInitialMonth = (month + 1)
                selectedInitialYear = year
                if (selectedFinalYear != 0) {
                    if (LocalDate.of(year, (month + 1), dayOfMonth)
                            .isBefore(
                                LocalDate.of(
                                    selectedFinalYear,
                                    selectedFinalMonth,
                                    selectedFinalDay
                                )
                            )
                    ) {
                        selectedInitialDateTextView.text =
                            "Initial date: $selectedInitialDay/$selectedInitialMonth/$selectedInitialYear"
                        dateInitial =
                            LocalDate.of(
                                selectedInitialYear,
                                selectedInitialMonth,
                                selectedInitialDay
                            )
                    }
                } else {
                    selectedInitialDateTextView.text =
                        "Initial date: $selectedInitialDay/$selectedInitialMonth/$selectedInitialYear"
                    dateInitial =
                        LocalDate.of(
                            selectedInitialYear,
                            selectedInitialMonth,
                            selectedInitialDay
                        )
                }
            } else {
                if (dateInitial.isBefore(LocalDate.of(year, (month + 1), dayOfMonth))) {
                    selectedFinalDay = dayOfMonth
                    selectedFinalMonth = (month + 1)
                    selectedFinalYear = year
                    selectedFinalDateTextView.text =
                        "Final date: $selectedFinalDay/$selectedFinalMonth/$selectedFinalYear"

                    daysBetween = ChronoUnit.DAYS.between(
                        LocalDate.of(
                            selectedInitialYear,
                            selectedInitialMonth,
                            selectedInitialDay
                        ),
                        LocalDate.of(
                            selectedFinalYear,
                            selectedFinalMonth,
                            selectedFinalDay
                        )
                    )

                    price = (coworkingPackage.price * daysBetween.toFloat() / 30.0f)

                    if (optionOneCheckBox.isChecked) {
                        price += (60 * daysBetween.toFloat() / 30.0f)
                    } else {
                        price -= (60 * daysBetween.toFloat() / 30.0f)
                    };

                    if (optionTwoCheckBox.isChecked) {
                        price += (70 * daysBetween.toFloat() / 30.0f)
                    } else {
                        price -= (70 * daysBetween.toFloat() / 30.0f)

                    };

                    if (optionThreeCheckBox.isChecked) {
                        price += (80 * daysBetween.toFloat() / 30.0f)
                    } else {
                        price -= (80 * daysBetween.toFloat() / 30.0f)
                    };

                    price = price.toInt().toFloat()
                    priceTextView.text = "$price lei"
                }
            }
        }
    }

    fun getSelectedOptions(): ArrayList<String> {
        var options = ArrayList<String>()

        if (optionOneCheckBox.isChecked)
            options.add(optionOneCheckBox.text as String)
        if (optionTwoCheckBox.isChecked)
            options.add(optionTwoCheckBox.text as String)
        if (optionThreeCheckBox.isChecked)
            options.add(optionThreeCheckBox.text as String)

        return options
    }
}