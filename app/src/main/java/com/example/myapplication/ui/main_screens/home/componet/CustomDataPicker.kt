package com.example.myapplication.ui.main_screens.home.component

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import java.util.*

@Composable
fun CustomDatePickerDialog(
    onDateSelected: (year: Int, month: Int, day: Int) -> Unit,
    specificDates: List<Calendar>
) {
    val context = LocalContext.current
    val calendar = remember { Calendar.getInstance() }

    val dateSetListener = DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, day: Int ->
        onDateSelected(year, month, day)
    }

    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            dateSetListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    // Open the DatePickerDialog
    datePickerDialog.show()

    // Customizing the DatePickerDialog to show a toast or some indication for specific dates
    datePickerDialog.setOnShowListener {
        val dp = datePickerDialog.datePicker
        for (date in specificDates) {
            val day = date.get(Calendar.DAY_OF_MONTH)
            val month = date.get(Calendar.MONTH)
            val year = date.get(Calendar.YEAR)

            // This is just an indication, as changing color of specific dates is not straightforward
            if (dp.year == year && dp.month == month && dp.dayOfMonth == day) {
                // Show a message or some indicator for specific dates
                android.widget.Toast.makeText(context, "Special Date: $day/${month + 1}/$year", android.widget.Toast.LENGTH_SHORT).show()
            }
        }
    }
}
