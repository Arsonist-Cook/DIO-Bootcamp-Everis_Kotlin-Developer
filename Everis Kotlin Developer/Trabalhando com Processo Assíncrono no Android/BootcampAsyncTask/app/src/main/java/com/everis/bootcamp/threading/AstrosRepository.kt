package com.everis.bootcamp.threading

import android.util.Log
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response

class AstrosRepository {

    fun loadData(): List<AstrosPeople>? {
        val client: OkHttpClient = OkHttpClient()
        val request: Request =
            Request.Builder().url("http://api.open-notify.org/astros.json").build()
        val response: Response = client.newCall(request).execute()
        val jsonBody = response.body?.string()
        val result = if(jsonBody != null) {
            Log.i("Parser", jsonBody)
            parseJSONtoObject(jsonBody)
        }else{
            null
        }

        return result?.people
    }

    private fun parseJSONtoObject(json:String?):AstrosResult? {
        var result:AstrosResult? = null
        if (json != null) {
            Log.i("Parser", json)
            result = Gson().fromJson(json, AstrosResult::class.java)
        }
        return result
    }
}