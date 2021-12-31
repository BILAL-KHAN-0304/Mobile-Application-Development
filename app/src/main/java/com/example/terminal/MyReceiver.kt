package com.example.terminal

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast

class MyReceiver: BroadcastReceiver(){
    override fun onReceive(p0: Context?, p1: Intent?) {
        val isAirplaneModeEnabled = p1?.getBooleanExtra("state",false )
        if(isAirplaneModeEnabled == true){
            Toast.makeText(p0, "Aeroplane mode enabled.", Toast.LENGTH_LONG ).show()
        }
        else{
            Toast.makeText(p0, "Aeroplane mode disabled.", Toast.LENGTH_LONG ).show()
        }
    }

}