package com.example.burak.calendar

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBar
import android.support.v7.app.ActionBarDrawerToggle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_event.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.daily_calendar.*
import java.text.DateFormatSymbols
import java.util.*
import javax.xml.datatype.DatatypeConstants.MONTHS
import android.view.LayoutInflater
import java.text.SimpleDateFormat


class SecondActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        val months = DateFormatSymbols().months
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_event)
        supportActionBar!!.hide()
        event_header.isCursorVisible = false
        event_header.backgroundTintList = ColorStateList.valueOf(0)

        event_header.setOnClickListener(this)
        activity_page.setOnClickListener(this)
        close_button.setOnClickListener(this)
        date_picker.setOnClickListener(this)
        time_picker.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.event_header -> {
                event_header.isCursorVisible = true
            }
            R.id.close_button -> {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.date_picker ->{
                DatePickerDialog()
            }
            R.id.time_picker -> {
                TimePickerDialog()
            }
            R.id.activity_page -> {
                event_header.isCursorVisible = false
                val view: View = if (currentFocus == null) View(this) else currentFocus
                val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            }
        }
    }

    fun DatePickerDialog (){
        val c = Calendar.getInstance()
        val day = c.get(Calendar.DAY_OF_MONTH)
        val month = c.get(Calendar.MONTH)
        val year = c.get(Calendar.YEAR)

        val dpd = DatePickerDialog(this, android.R.style.Theme_DeviceDefault_Dialog, DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
            date_picker.text = "$dayOfMonth " + months[monthOfYear] + " $year"
        }, year, month, day)

        dpd.show()
    }

    fun  TimePickerDialog(){
        val c = Calendar.getInstance();
        val hour = c.get(Calendar.HOUR_OF_DAY);
        val minute = c.get(Calendar.MINUTE);

                val tpd = TimePickerDialog(this, android.R.style.Theme_Holo_Dialog , TimePickerDialog.OnTimeSetListener{timePicker, hour, minute ->
            time_picker.text = "$hour:00"
        }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), true)

        tpd.show()
    }
}

