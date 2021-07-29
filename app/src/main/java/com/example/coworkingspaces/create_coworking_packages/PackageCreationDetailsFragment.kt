package com.example.coworkingspaces.create_coworking_packages

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.coworkingspaces.R
import com.example.coworkingspaces.coworking_packages.PopulateCoworkingPackages


class PackageCreationDetailsFragment: Fragment(), AdapterView.OnItemSelectedListener {

    lateinit var root : View
    lateinit var currency : String
    var features : MutableList<EditText> = mutableListOf<EditText>()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        root = inflater.inflate(R.layout.package_creation, container, false)

        val spinner: Spinner = root.findViewById(R.id.spinner_currency)
        spinner.onItemSelectedListener = this
        ArrayAdapter.createFromResource(
                this.requireContext(),
                R.array.currency_array,
                android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }

        val addFeatureButton = root.findViewById<Button>(R.id.buttonAddFeature)
        val createPackageButton = root.findViewById<Button>(R.id.buttonCreatePackage)

        addFeatureButton.setOnClickListener(View.OnClickListener {
            val featureEditText = EditText(this.requireContext())
            featureEditText.setHint("Feature")
            var params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                    .also { it.setMargins(90, 0, 0, 0) }
            featureEditText.layoutParams = params
            val component = root.findViewById(R.id.features) as LinearLayout
            features.add(featureEditText)
            component.addView(featureEditText)
        })
        createPackageButton.setOnClickListener(View.OnClickListener {
            var allGood: Boolean = true
            val title = root.findViewById<EditText>(R.id.editTextPackageName)
            val description = root.findViewById<EditText>(R.id.editTextPackageDetails)
            val price = root.findViewById<EditText>(R.id.editTextPackagePrice)
            val feature1 = root.findViewById<EditText>(R.id.editTextFeature1)

            if (title.getText().toString().trim().equals("")) {
                title.setError("Package name is required!");
                allGood = false
            }
            if (description.getText().toString().trim().equals("")) {
                description.setError("Package description is required!");
                allGood = false
            }
            if (price.getText().toString().trim().equals("")) {
                price.setError("Price is required!");
                allGood = false
            }

            if (allGood) {

                var featuresList: MutableList<String> = mutableListOf()
                var f1: String = feature1.text.toString()
                featuresList.add(f1)
                for (f in features) {
                    featuresList.add(f.text.toString())
                }

                var t: String = title.text.toString()
                var d: String = description.text.toString()
                var p: String = price.text.toString()
                Package.name = t
                Package.description = d
                Package.features = featuresList
                Package.price = p
                Package.currency = currency

                val builder1 = AlertDialog.Builder(context)
                builder1.setTitle("Package creation confirmation")
                builder1.setMessage("Are you sure you want to create the package? ")
                builder1.setCancelable(true)

                builder1.setPositiveButton(
                        "Yes") { dialog, which ->

                    val fragment: Fragment = PackageCreatedConfirmation()
                    replaceFragment(fragment)

                    var featuresArrayString = ArrayList<String>()

                    for(featureMutable in featuresList) {
                        featuresArrayString.add(featureMutable)
                    }

                    PopulateCoworkingPackages.addNewPackage(Package.image, Package.name, Package.description, Package.price.toFloat(), featuresArrayString)

                }

                builder1.setNegativeButton(
                        "Go back to edit"
                ) { dialog, id -> dialog.cancel() }

                val alert11 = builder1.create()
                alert11.show()




              //  for(f in Package.features)
                //    Log.d("Features", f)
            }
        })
        return root;
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        if (parent != null) {
            currency = parent.getItemAtPosition(position).toString()
        }
        Log.d("TAG:", currency)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        TODO("Not yet implemented")
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