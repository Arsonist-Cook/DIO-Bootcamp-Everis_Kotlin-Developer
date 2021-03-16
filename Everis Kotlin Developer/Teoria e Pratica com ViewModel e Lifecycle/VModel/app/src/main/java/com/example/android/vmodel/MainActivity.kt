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
import androidx.databinding.DataBindingUtil
import com.example.android.vmodel.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    private val mViewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setListeners()
    }

    private fun setListeners() {
        binding.apply {
            btnCounter.setOnClickListener {
                mViewModel.count()
            }
            invalidateAll()
            btnShowView.setOnClickListener {
                Toast.makeText(
                        applicationContext,
                        "Contador: ${mViewModel.mCounter.value}",
                        Toast.LENGTH_SHORT
                ).show()
            }
        }
        mViewModel.mCounter.observe(this, Observer { binding.tvCounter.text = it })
    }


}