package com.example.myapplication

import android.app.*
import android.os.Bundle
import android.widget.TextView
import android.widget.DatePicker
import java.text.DateFormat
import java.util.Calendar


class DatePickerFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {

    private lateinit var calendar:Calendar

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // Initialize a calendar instance
        calendar = Calendar.getInstance()

        // Get the system current date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)


        // Initialize a new date picker dialog and return it
        return DatePickerDialog(
            activity, // Context
            android.R.style.Theme_Holo_Light_Dialog_NoActionBar_MinWidth, // Theme
            this, // DatePickerDialog.OnDateSetListener
            year, // Year
            month, // Month of year
            day // Day of month
        )
    }


    // When date set and press ok button in date picker dialog
    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val minimumSaving: TextView = activity.findViewById(R.id.minSavingText)
        val withdraw: TextView = activity.findViewById(R.id.withdrawText)
        // Display the selected date in text view
        activity.findViewById<TextView>(R.id.dobText).text = String.format("Date of birth: " + formatDate(year,month,day))
        activity.findViewById<TextView>(R.id.ageText).text = String.format("Age: "+ getAge(year,month,day))
        val num = Integer.parseInt(getAge(year, month, day))
        val minimumAmount = when(num){
            in 16..20 -> 5000
            in 21..25 -> 14000
            in 26..30 -> 29000
            in 31..35 -> 50000
            in 36..40 -> 78000
            in 41..45 -> 116000
            in 46..50 -> 165000
            in 51..55 -> 228000
            else -> 0
        }
        val maximumWithdrawAmount = when(num){
            in 16..20 -> 1500
            in 21..25 -> 4200
            in 26..30 -> 8700
            in 31..35 -> 15000
            in 36..40 -> 23400
            in 41..45 -> 34800
            in 46..50 -> 49500
            in 51..55 -> 68400
            else -> 0
        }
        minimumSaving.text = "RM" + minimumAmount
        withdraw.text = "RM" + maximumWithdrawAmount
    }

    // Custom method to format date
    private fun formatDate(year:Int, month:Int, day:Int):String{
        // Create a Date variable/object with user chosen date
        calendar.set(year, month, day, 0, 0, 0)
        val chosenDate = calendar.time
        // Format the date picker selected date
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM)
        return df.format(chosenDate)
    }
    private fun getAge(year:Int, month:Int, day:Int):String {
        val dob = Calendar.getInstance()
        val today = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        dob.set(year, month, day)
        var age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR)
        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR))
        {
            age--
        }
        val ageInt = age
        val ageS = ageInt.toString()

        return ageS
    }
}