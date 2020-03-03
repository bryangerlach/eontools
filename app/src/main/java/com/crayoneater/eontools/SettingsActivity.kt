package com.crayoneater.eontools

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView


class SettingsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    val PREFS_FILENAME = "com.crayoneater.eontools.prefs"
    var prefs: SharedPreferences? = null
    var etDevice: EditText? = null
    var etJwt: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.settings)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.settings)
        navView = findViewById(R.id.nv)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, 0, 0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        prefs = this.getSharedPreferences(PREFS_FILENAME, 0)

        val deviceId = prefs!!.getString("devid","")
        val jwtToken = prefs!!.getString("jwt","")

        etDevice = findViewById<EditText>(R.id.et_device)
        etJwt = findViewById<EditText>(R.id.et_jwt)

        etDevice!!.setText(deviceId)
        etJwt!!.setText(jwtToken)

        val btnGetdevid = findViewById<Button>(R.id.btn_getdevid)
        btnGetdevid.setOnClickListener {
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://my.comma.ai/")
            startActivity(openURL)
        }

        val btnGetjwt = findViewById<Button>(R.id.btn_getjwt)
        btnGetjwt.setOnClickListener {
            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse("https://jwt.comma.ai/")
            startActivity(openURL)
        }

        val btnSave = findViewById<Button>(R.id.btn_save)
        btnSave.setOnClickListener {
            save()
        }
    }

    private fun save() {
        val editor = prefs!!.edit()
        editor.putString("devid", etDevice?.getText().toString())
        editor.putString("jwt", etJwt?.getText().toString())
        editor.apply()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.tools -> {
                save()
            }
            R.id.settings -> {
                save()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}