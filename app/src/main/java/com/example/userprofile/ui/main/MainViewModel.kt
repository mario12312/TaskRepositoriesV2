package com.example.userprofile.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.*
import org.json.JSONObject
import java.io.IOException


class MainViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    val jsonArraytxt: MutableLiveData<String> = MutableLiveData("")
    var lst = MutableLiveData<ArrayList<Repository>>()
    var newlist = arrayListOf<Repository>()

    //declaring okhttp client variable
    var client = OkHttpClient()

    //requesting public Git Repositories by a OkHttp GET request
    fun run() {
        val request = Request.Builder()
                .url("https://api.github.com/users/public/repos?page=1")
                .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")
                    var resp = response.body!!.string()
                    //convert the response from JsonArray to Json Objects by removing '[' and ']'
                    resp = resp.replace("[","")
                    resp = resp.replace("]","")
                    val jsonArrayobjs = JSONObject(resp)
                    //'owner' is a jsonobject that has objects inside so I removed it to convert the string
                    //to one big Json Object
                    JSONObject(jsonArrayobjs.remove("owner").toString())
                    //each jsonobject has key and value
                    //so I went through all keys to import
                    val keys: Iterator<String> = jsonArrayobjs.keys()
                    while (keys.hasNext()){
                        val key = keys.next()
                        if (key.contains("_url")) {
                            val repository = Repository(jsonArrayobjs.get(key).toString())
                            newlist.add(repository)
                            lst.postValue(newlist)
                        }
                    }
                }
            }
        })
        Thread.sleep(2000)
    }
}
