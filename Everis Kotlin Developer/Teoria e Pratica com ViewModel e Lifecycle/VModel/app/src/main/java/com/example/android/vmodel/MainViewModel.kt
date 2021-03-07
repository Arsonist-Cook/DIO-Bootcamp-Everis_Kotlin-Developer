package com.example.android.vmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private var counter: Int = 0
    val mCounter: MutableLiveData<String> = MutableLiveData<String>().apply { value = counter.toString() }

    fun count() {
        counter = validateCounterLimit(++counter)
        mCounter.value = counter.toString()
    }

    private fun validateCounterLimit(counter: Int): Int {
        var validatedResult: Int = 0;
        if (counter <= 5) {
            validatedResult = counter
        }
        return validatedResult
    }

}