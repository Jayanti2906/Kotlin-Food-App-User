package com.jayanti.myswayuser

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

class DateSelectorClass(val context : Context, textView: TextView)
{
    var cal = Calendar.getInstance()

    val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
        cal.set(Calendar.YEAR, year)
        cal.set(Calendar.MONTH, monthOfYear)
        cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

        val myFormat = "dd.MM.yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        dateString = sdf.format(cal.time).toString()
        textView.text =  dateString
        Log.e("SEEE DATE >>>>","$dateString")
    }



    fun showCalender()
    {
        DatePickerDialog(
            context, dateSetListener,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH)
        ).show()
    }
 // calculatig the date of setting the menu


}