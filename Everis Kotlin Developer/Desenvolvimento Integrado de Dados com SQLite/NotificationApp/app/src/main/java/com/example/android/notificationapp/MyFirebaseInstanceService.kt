package com.example.android.notificationapp

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

class MyFirebaseInstanceService: FirebaseInstanceIdService(){
    override fun onTokenRefresh() {
        Log.w("**newToken", FirebaseInstanceId.getInstance().token.toString())
    }
}