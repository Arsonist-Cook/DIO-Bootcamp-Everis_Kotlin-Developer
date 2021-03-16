package com.example.android.notificationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.google.firebase.iid.FirebaseInstanceId

class MainActivity : AppCompatActivity() {
    val btnSend: Button by lazy{
        findViewById<Button>(R.id.button_send_notification)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSend.setOnClickListener {
            this.showNotification("1234", "Bootcamp Android", "Kotlin Android Curso!")
        }
        Log.i("**newToken", FirebaseInstanceId.getInstance().token.toString())
    }
}