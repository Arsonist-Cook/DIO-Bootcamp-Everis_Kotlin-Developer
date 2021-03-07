package com.example.android.vmodel

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private val tvCounter: TextView by lazy {
        findViewById<TextView>(R.id.tv_counter)
    }

    private val btnCounter: Button by lazy {
        findViewById<Button>(R.id.btn_counter)
    }

    private val btnShowView: Button by lazy {
        findViewById<Button>(R.id.btn_showView)
    }

    private val mViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setListeners()
    }

    private fun setListeners() {
        btnCounter.setOnClickListener {
            mViewModel.count()
        }
        btnShowView.setOnClickListener {
            Toast.makeText(
                applicationContext,
                "Contador: ${mViewModel.mCounter.value}",
                Toast.LENGTH_SHORT
            ).show()
        }
        mViewModel.mCounter.observe(this, Observer { tvCounter.text = it })
    }


}