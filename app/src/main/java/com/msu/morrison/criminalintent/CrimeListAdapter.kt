package com.msu.morrison.criminalintent

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.msu.morrison.criminalintent.databinding.ListItemCrimeBinding
import com.msu.morrison.criminalintent.databinding.ListItemCrimePoliceBinding

class CrimeHolder(private val binding: ListItemCrimeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.root.setOnClickListener {
            // Handle click event
        }
    }
}

class PoliceCrimeHolder(private val binding: ListItemCrimePoliceBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(crime: Crime) {
        binding.crimeTitle.text = crime.title
        binding.crimeDate.text = crime.date.toString()

        binding.contactPoliceButton.setOnClickListener {
            // Handle contact police button click
        }
    }
}

class CrimeListAdapter(private val crimes: List<Crime>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_NORMAL = 0
        private const val VIEW_TYPE_POLICE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_NORMAL -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ListItemCrimeBinding.inflate(inflater, parent, false)
                CrimeHolder(binding)
            }
            VIEW_TYPE_POLICE -> {
                val inflater = LayoutInflater.from(parent.context)
                val binding = ListItemCrimePoliceBinding.inflate(inflater, parent, false)
                PoliceCrimeHolder(binding)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val crime = crimes[position]
        when (holder) {
            is CrimeHolder -> holder.bind(crime)
            is PoliceCrimeHolder -> holder.bind(crime)
        }
    }

    override fun getItemCount() = crimes.size

    override fun getItemViewType(position: Int): Int {
        val crime = crimes[position]
        return if (crime.requiresPolice) VIEW_TYPE_POLICE else VIEW_TYPE_NORMAL
    }
}
