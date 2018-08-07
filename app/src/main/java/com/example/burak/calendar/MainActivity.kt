package com.example.burak.calendar

import android.content.Intent
import android.os.AsyncTask
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.daily_calendar.*
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat
import java.time.LocalDateTime


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    companion object {
        val months = DateFormatSymbols().months
        var currentYear = LocalDateTime.now().year.toString()
        var currentMonth = LocalDateTime.now().month.toString()
        var currentDay = String.format ("%d", LocalDateTime.now().dayOfMonth.toInt())
        val serverurl = "https://floating-hollows-92820.herokuapp.com/all"
        var eventname = arrayListOf<String>()
        var eventdateday = arrayListOf<String>()
        var eventdatemonth = arrayListOf<String>()
        var eventdateyear = arrayListOf<String>()
        var eventtime = arrayListOf<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        toolbar.title = months[SimpleDateFormat("MM").format(calendar.date).toInt()-1]
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        ServerCommunication().execute(serverurl)

        currentMonth = months[SimpleDateFormat("MM").format(calendar.date).toInt()-1]
        currentDay = SimpleDateFormat("dd").format(calendar.date)

        calendar.setOnDateChangeListener({ view, year, month, day ->
            toolbar.title = months[month]
            currentMonth = months[month]
            currentDay = String.format ("%d", day)
        })

        nav_view.setNavigationItemSelectedListener(this)
        fab.setOnClickListener(this)

    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab -> {
                val intent = Intent(applicationContext, SecondActivity::class.java)
                startActivity(intent)
            }
        }


    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return true;
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.daily -> {
                ServerCommunication().execute(serverurl)
                M_calendar.visibility = View.INVISIBLE
                D_calendar.visibility = View.VISIBLE
                toolbar.title = "$currentDay $currentMonth"
                fillDaily()
            }
            R.id.monthly -> {
                M_calendar.visibility = View.VISIBLE
                D_calendar.visibility = View.INVISIBLE
                toolbar.title = currentMonth
                vibilychange()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    fun vibilychange(){
        hour0.visibility = View.INVISIBLE
        hour1.visibility = View.INVISIBLE
        hour2.visibility = View.INVISIBLE
        hour3.visibility = View.INVISIBLE
        hour4.visibility = View.INVISIBLE
        hour5.visibility = View.INVISIBLE
        hour6.visibility = View.INVISIBLE
        hour7.visibility = View.INVISIBLE
        hour8.visibility = View.INVISIBLE
        hour9.visibility = View.INVISIBLE
        hour10.visibility = View.INVISIBLE
        hour11.visibility = View.INVISIBLE
        hour12.visibility = View.INVISIBLE
        hour13.visibility = View.INVISIBLE
        hour14.visibility = View.INVISIBLE
        hour15.visibility = View.INVISIBLE
        hour16.visibility = View.INVISIBLE
        hour17.visibility = View.INVISIBLE
        hour18.visibility = View.INVISIBLE
        hour19.visibility = View.INVISIBLE
        hour20.visibility = View.INVISIBLE
        hour21.visibility = View.INVISIBLE
        hour22.visibility = View.INVISIBLE
        hour23.visibility = View.INVISIBLE

    }

    fun fillDaily(){
        for (i :String in eventtime){
            if(eventdateday.get(eventtime.indexOf(i)).toInt() == currentDay.toInt()) {
                when (i) {
                    "0" -> {
                        hour0.text = eventname.get(eventtime.indexOf(i))
                        hour0.visibility = View.VISIBLE
                    }
                    "1" -> {
                        hour1.text = eventname.get(eventtime.indexOf(i))
                        hour1.visibility = View.VISIBLE
                    }
                    "2" -> {
                        hour2.text = eventname.get(eventtime.indexOf(i))
                        hour2.visibility = View.VISIBLE
                    }
                    "3" -> {
                        hour3.text = eventname.get(eventtime.indexOf(i))
                        hour3.visibility = View.VISIBLE
                    }
                    "4" -> {
                        hour4.text = eventname.get(eventtime.indexOf(i))
                        hour4.visibility = View.VISIBLE
                    }
                    "5" -> {
                        hour5.text = eventname.get(eventtime.indexOf(i))
                        hour5.visibility = View.VISIBLE
                    }
                    "6" -> {
                        hour6.text = eventname.get(eventtime.indexOf(i))
                        hour6.visibility = View.VISIBLE
                    }
                    "7" -> {
                        hour7.text = eventname.get(eventtime.indexOf(i))
                        hour7.visibility = View.VISIBLE
                    }
                    "8" -> {
                        hour8.text = eventname.get(eventtime.indexOf(i))
                        hour8.visibility = View.VISIBLE
                    }
                    "9" -> {
                        hour9.text = eventname.get(eventtime.indexOf(i))
                        hour9.visibility = View.VISIBLE
                    }
                    "10" -> {
                        hour10.text = eventname.get(eventtime.indexOf(i))
                        hour10.visibility = View.VISIBLE
                    }
                    "11" -> {
                        hour11.text = eventname.get(eventtime.indexOf(i))
                        hour11.visibility = View.VISIBLE
                    }
                    "12" -> {
                        hour12.text = eventname.get(eventtime.indexOf(i))
                        hour12.visibility = View.VISIBLE
                    }
                    "13" -> {
                        hour13.text = eventname.get(eventtime.indexOf(i))
                        hour13.visibility = View.VISIBLE
                    }
                    "14" -> {
                        hour14.text = eventname.get(eventtime.indexOf(i))
                        hour14.visibility = View.VISIBLE
                    }
                    "15" -> {
                        hour15.text = eventname.get(eventtime.indexOf(i))
                        hour15.visibility = View.VISIBLE
                    }
                    "16" -> {
                        hour16.text = eventname.get(eventtime.indexOf(i))
                        hour16.visibility = View.VISIBLE
                    }
                    "17" -> {
                        hour17.text = eventname.get(eventtime.indexOf(i))
                        hour17.visibility = View.VISIBLE
                    }
                    "18" -> {
                        hour18.text = eventname.get(eventtime.indexOf(i))
                        hour18.visibility = View.VISIBLE
                    }
                    "19" -> {
                        hour19.text = eventname.get(eventtime.indexOf(i))
                        hour19.visibility = View.VISIBLE
                    }
                    "20" -> {
                        hour20.text = eventname.get(eventtime.indexOf(i))
                        hour20.visibility = View.VISIBLE
                    }
                    "21" -> {
                        hour21.text = eventname.get(eventtime.indexOf(i))
                        hour21.visibility = View.VISIBLE
                    }
                    "22" -> {
                        hour22.text = eventname.get(eventtime.indexOf(i))
                        hour22.visibility = View.VISIBLE
                    }
                    "23" -> {
                        hour23.text = eventname.get(eventtime.indexOf(i))
                        hour23.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    inner class ServerCommunication : AsyncTask<String, String, String>() {

        override fun onPreExecute() {
            // Before doInBackground
        }

        override fun doInBackground(vararg urls: String?): String? {

            var connection: HttpURLConnection? = null
            try {
                val url = URL(urls[0])

                connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                var inString = translate(connection.inputStream)
                publishProgress(inString)
            } catch (ex: Exception) {

            } finally {
                if (connection != null) {
                    connection.disconnect()
                }
            }

            return " "
        }
    }

    fun translate(inputStream: InputStream): String {
        val bufferReader = BufferedReader(InputStreamReader(inputStream))
        var line: String
        var result = ""
        var counter =0;

        try {
            do {
                line = bufferReader.readLine().replace("\\s+".toRegex(), "")
                if (line != null && line.length > line.substringAfter("<div>").length) {
                    line = line.substringAfter("<div>").substringBefore("</div>").substringBefore(":")
                    if(counter%3==0){
                        eventname.add(line)

                    }
                    else if (counter%3 == 1){
                        for(j:String in months) {
                            if(line.length > line.substringAfter(j).length) {
                                eventdateday.add(line.substringBefore(j))
                                eventdatemonth.add(j)
                                eventdateyear.add(line.substringAfter(j))

                            }
                        }
                    }
                    else{
                        eventtime.add(line)
                    }
                    result += line

                    counter ++
                }
            } while (line != null)
            inputStream.close()
        } catch (ex: Exception) {

        }

        return result
    }
}
