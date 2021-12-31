package com.example.terminal

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.example.terminal.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    lateinit var receiver: BroadcastReceiver
    //lateinit var myAdapter:CustomAdapter
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //---------------Services-------------
        startService(Intent(this, MainActivity::class.java))

        receiver = MyReceiver()

        IntentFilter(Intent.ACTION_AIRPLANE_MODE_CHANGED).also{
            registerReceiver(receiver,it)
        }
        //--------------Binding---------------
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        //---------------Volley---------------

        val queue = Volley.newRequestQueue(this)
        val url = "https://run.mocky.io/v3/b8402fc5-ae31-4d98-bced-b5b3fede6d06"
        val arrayList = ArrayList<studentInfo>()
        val rView = findViewById<RecyclerView>(R.id.rView)

        val jsonArrayRequest = JsonArrayRequest(Request.Method.GET, url, null,
            { response ->

                for (i in 0..response.length()-1)
                {
                    var key = JSONObject(response[i].toString())
                    arrayList.add(studentInfo(key.get("name").toString(), key.get("registrationNumber").toString(), key.get("phoneNumber").toString()))
                }
                rView.adapter = CustomAdapter(arrayList, this)

            },
            { error ->
                    Log.i("RequestError", "${error}")
            }
        )

        rView.layoutManager = LinearLayoutManager(this)
        //rView.adapter = CustomAdapter(arrayList, this)

        queue.add(jsonArrayRequest)
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }
}