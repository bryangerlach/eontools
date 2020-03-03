package com.crayoneater.eontools

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.android.material.navigation.NavigationView
import org.json.JSONArray
import org.json.JSONObject


class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var navView: NavigationView
    val PREFS_FILENAME = "com.crayoneater.eontools.prefs"
    var prefs: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.activity_main)
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
        val url = "https://athena.comma.ai/"+deviceId

        val tvDevid = findViewById<TextView>(R.id.tv_devid)
        val tvJwt = findViewById<TextView>(R.id.tv_jwt)

        tvDevid.setText(deviceId)
        tvJwt.setText(jwtToken)

        val btnSnapshot = findViewById(R.id.btn_snapshot) as Button
        btnSnapshot.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val jsonObject = JSONObject()

            jsonObject.put("method","takeSnapshot")
            jsonObject.put("jsonrpc", "2.0")
            jsonObject.put("id",0)

            val jsonObjectRequest = object: JsonObjectRequest(
                Request.Method.POST, url,jsonObject,
                Response.Listener<JSONObject> { response ->
                    Log.d("A", "Response is: " + response)
                },
                Response.ErrorListener {  }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("Authorization", "JWT "+jwtToken)
                    return headers
                }
            }

            queue.add(jsonObjectRequest)

        }

        val btnGitpull = findViewById(R.id.btn_gitpull) as Button
        btnGitpull.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val jsonArray = JSONArray()
            val jsonObject = JSONObject()
            val jsonObject2 = JSONObject()
            jsonObject2.put("command", "gitpull")
            jsonObject.put("method","custom")
            jsonObject.put("params",jsonObject2)
            jsonObject.put("jsonrpc", "2.0")
            jsonObject.put("id",0)
            jsonArray.put(jsonObject)

            val jsonObjectRequest = object: JsonObjectRequest(
                Request.Method.POST, url,jsonObject,
                Response.Listener<JSONObject> { response ->
                    Log.d("A", "Response is: " + response)
                },
                Response.ErrorListener {  }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("Authorization", "JWT "+jwtToken)
                    return headers
                }
            }

            queue.add(jsonObjectRequest)

        }

        val btnReboot = findViewById(R.id.btn_reboot) as Button
        btnReboot.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val jsonArray = JSONArray()
            val jsonObject = JSONObject()
            jsonObject.put("method","reboot")
            jsonObject.put("jsonrpc", "2.0")
            jsonObject.put("id",0)
            jsonArray.put(jsonObject)

            val jsonObjectRequest = object: JsonObjectRequest(
                Request.Method.POST, url,jsonObject,
                Response.Listener<JSONObject> { response ->
                    Log.d("A", "Response is: " + response)
                },
                Response.ErrorListener {  }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("Authorization", "JWT "+jwtToken)
                    return headers
                }
            }

            queue.add(jsonObjectRequest)

        }

        val btnAutoecu = findViewById(R.id.btn_autoecu) as Button
        btnAutoecu.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val jsonArray = JSONArray()
            val jsonObject = JSONObject()
            val jsonObject2 = JSONObject()
            jsonObject2.put("command", "autoecu")
            jsonObject.put("method","custom")
            jsonObject.put("params",jsonObject2)
            jsonObject.put("jsonrpc", "2.0")
            jsonObject.put("id",0)
            jsonArray.put(jsonObject)

            val jsonObjectRequest = object: JsonObjectRequest(
                Request.Method.POST, url,jsonObject,
                Response.Listener<JSONObject> { response ->
                    Log.d("A", "Response is: " + response)
                },
                Response.ErrorListener {  }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("Authorization", "JWT "+jwtToken)
                    return headers
                }
            }

            queue.add(jsonObjectRequest)

        }

        val btnTerm = findViewById(R.id.btn_term) as Button
        btnTerm.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val jsonArray = JSONArray()
            val jsonObject = JSONObject()
            val jsonObject2 = JSONObject()
            jsonObject2.put("command", "terminal")
            jsonObject.put("method","custom")
            jsonObject.put("params",jsonObject2)
            jsonObject.put("jsonrpc", "2.0")
            jsonObject.put("id",0)
            jsonArray.put(jsonObject)

            val jsonObjectRequest = object: JsonObjectRequest(
                Request.Method.POST, url,jsonObject,
                Response.Listener<JSONObject> { response ->
                    Log.d("A", "Response is: " + response)
                },
                Response.ErrorListener {  }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("Authorization", "JWT "+jwtToken)
                    return headers
                }
            }

            queue.add(jsonObjectRequest)

        }

        val btnSettings = findViewById(R.id.btn_settings) as Button
        btnSettings.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val jsonArray = JSONArray()
            val jsonObject = JSONObject()
            val jsonObject2 = JSONObject()
            jsonObject2.put("command", "android_settings")
            jsonObject.put("method","custom")
            jsonObject.put("params",jsonObject2)
            jsonObject.put("jsonrpc", "2.0")
            jsonObject.put("id",0)
            jsonArray.put(jsonObject)

            val jsonObjectRequest = object: JsonObjectRequest(
                Request.Method.POST, url,jsonObject,
                Response.Listener<JSONObject> { response ->
                    Log.d("A", "Response is: " + response)
                },
                Response.ErrorListener {  }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("Authorization", "JWT "+jwtToken)
                    return headers
                }
            }

            queue.add(jsonObjectRequest)

        }

        val btnKillall = findViewById(R.id.btn_killall) as Button
        btnKillall.setOnClickListener {
            val queue = Volley.newRequestQueue(this)
            val jsonArray = JSONArray()
            val jsonObject = JSONObject()

            val jsonObject2 = JSONObject()
            jsonObject2.put("command", "killall")
            jsonObject.put("method","custom")
            jsonObject.put("params",jsonObject2)
            jsonObject.put("jsonrpc", "2.0")
            jsonObject.put("id",0)
            jsonArray.put(jsonObject)

            val jsonObjectRequest = object: JsonObjectRequest(
                Request.Method.POST, url,jsonObject,
                Response.Listener<JSONObject> { response ->
                    Log.d("A", "Response is: " + response)
                },
                Response.ErrorListener {  }) {

                override fun getHeaders(): MutableMap<String, String> {
                    val headers = HashMap<String, String>()
                    headers.put("Authorization", "JWT "+jwtToken)
                    return headers
                }
            }

            queue.add(jsonObjectRequest)

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.tools -> {
            }
            R.id.settings -> {
                val intent = Intent(this, SettingsActivity::class.java)
                startActivity(intent)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}
