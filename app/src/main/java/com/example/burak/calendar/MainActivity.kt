package com.example.burak.calendar

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.burak.calendar.R.string.app_name
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.add_event_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.daily_calendar.*
import java.text.DateFormatSymbols
import java.text.SimpleDateFormat


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        val months = DateFormatSymbols().months
        toolbar.title = months[SimpleDateFormat("MM").format(calendar.date).toInt()-1]
        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        calendar.setOnDateChangeListener({ view, year, month, dayOfMonth ->
            toolbar.title = months[month]
        })

        nav_view.setNavigationItemSelectedListener(this)
        fab.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.fab -> {

                calendar.visibility = View.INVISIBLE
                calendar2.visibility = View.VISIBLE
                toolbar.title = toolbar.title.toString() + " " + (SimpleDateFormat("dd").format(calendar.date))

                /*val intent = Intent(applicationContext, SecondActivity::class.java)
                startActivity(intent)*/
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
            R.id.nav_camera -> {
                // Handle the camera action
            }
            R.id.nav_gallery -> {

            }
            R.id.nav_slideshow -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
