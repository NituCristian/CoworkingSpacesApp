package com.example.coworkingspaces.coworking_packages

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coworkingspaces.R

class CoworkingPackagesViewAdapter(
    private val context: Context,
    private val coworkingPackages: ArrayList<CoworkingPackage>
) : RecyclerView.Adapter<PackagesViewHolder>() {
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PackagesViewHolder {
        val view = inflater.inflate(viewType, parent, false);
        return PackagesViewHolder(coworkingPackages, context, view)
    }

    override fun onBindViewHolder(holder: PackagesViewHolder, position: Int) {
        holder.setPackagePosition(position)
        holder.bindData(coworkingPackages[position])
    }

    override fun getItemCount(): Int {
        return coworkingPackages.size
    }

    override fun getItemViewType(position: Int): Int {
        return R.layout.coworking_package_card
    }
}