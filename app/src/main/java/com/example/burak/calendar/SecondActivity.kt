package com.example.burak.calendar

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.content.res.ColorStateList
import android.net.Uri
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_event.*
import okhttp3.*
import java.text.DateFormatSymbols
import java.util.*
import java.net.URLConnection
import android.os.AsyncTask.execute
import com.example.burak.calendar.R.id.*
import org.json.JSONObject
import java.io.*
import java.lang.reflect.InvocationTargetException
import java.net.HttpURLConnection
import java.net.URL
import kotlin.collections.ArrayList


class SecondActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        val months = DateFormatSymbols().months
        val serverurl = "https://agile-caverns-47941.herokuapp.com/posts"
        val JSON = MediaType.parse("application/json; charset=utf-8")
        val pname = "post="
        var name = "event_name="
        var date = "&event_date="
        var time = "&event_time="

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
        save_button.setOnClickListener(this)
    }

    inner class ServerCommunication : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            // Before doInBackground
        }

        override fun doInBackground(vararg urls: String?): String? {

            var params:ArrayList<String> ?= null

            var connection: HttpURLConnection? = null
            var silincek = " "

            println("Geldi "+ urls[0])
            try {
                val url = URL(urls[0])

                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "POST"

                val post = DataOutputStream(connection.outputStream)

                post.writeBytes(name + event_header.text + date + date_picker.text + time + time_picker.text)
                post.flush()
                post.close()
                var inString = streamToString(connection.inputStream)
                silincek = inString
                publishProgress(inString)
            }catch (ex: Exception) {

            }finally {
                if (connection != null) {
                    connection.disconnect()
                }
            }

            return silincek
        }

        override fun onProgressUpdate(vararg values: String?) {
            try {
                var json = JSONObject(values[0])

                val query = json.getJSONObject("query")
                val results = query.getJSONObject("results")
                val channel = results.getJSONObject("channel")

                val location = channel.getJSONObject("location")
                val city = location.get("event_name")

                date_picker.text =
                        "event_name: " + city
                println("Geldi => ")

            } catch (ex: Exception) {

            }
        }

        override fun onPostExecute(result: String?) {
            // ...
        }
    }

    fun streamToString(inputStream: InputStream): String {
        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        var line: String
        var result = ""

        try {
            do {
                line = bufferReader.readLine()
                if (line != null) {
                    result += line
                    println("Geldi => " + line)
                }
            } while (line != null)
            inputStream.close()
        } catch (ex: Exception) {

        }

        return result
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
            R.id.save_button -> {
                ServerCommunication().execute(serverurl)
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


