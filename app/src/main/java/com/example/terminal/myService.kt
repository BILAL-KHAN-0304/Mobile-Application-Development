package com.example.terminal

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.Toast

class myService : Service(){
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
       Log.i("Status", "I am Started")
        return START_STICKY
    }

    override fun onDestroy() {
        Log.i("Status", "I am destroyed")
    }

}