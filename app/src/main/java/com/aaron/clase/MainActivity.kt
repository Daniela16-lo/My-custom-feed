package com.aaron.clase

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.io.IOException
import java.lang.Exception
import java.net.MalformedURLException
import java.net.URL
import java.time.LocalDate
import kotlin.properties.Delegates

class FeedEntry{
    var name: String = ""
    var artist: String = ""
    var releaseDate: String = ""
    var summary: String = ""
    var imageURL: String = ""

    override fun toString(): String {
        return """
            name = $name
            artist = $artist
            releaseDate = $releaseDate
            imageURL = $imageURL
            """.trimIndent()
    }
}


class MainActivity : AppCompatActivity() {
    private val TAG =   "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.xalRecyclerView)

        Log.d(TAG, "onCreate")

        val downloadData = DownloadData(this, recyclerView)
        downloadData.execute("url")
        Log.d(TAG "onCreate DONE")

    }

    companion object{
        private class DownloadData(context: Context, recyclerView: RecyclerView) : AsyncTask<String, Void, String>(){
            private val TAG = "DownloadData"

            var localContext: Context by Delegates.notNull()
            var localRecyclerView: RecyclerView by Delegates.notNull()

            init {
                localContext = context
                localRecyclerView = recyclerView
            }
            override fun doInBackground(vararg url: String?): String {
                Log.d(TAG, "doInBackground")
                val rssFeed = dowloadXML(url[0])
                if (rssFeed.isEmpty()){
                    Log.e(TAG, "doInBackground: failed")
                }
                Log.d(TAG, rssFeed)
                return rssFeed
            }

            override fun onPostExecute(result: String?) {
                super.onPostExecute(result)
                Log.d(TAG, "onPostExecute")
                val parsedApplication = ParseApplication()
                parsedApplication.parse(result)

                val adapter:AppAdapter = AppAdapter(localContext, parsedApplication.applications)
                localRecyclerView.adapter = adapter
                localRecyclerView.layoutManager = LinearLayoutManager(localContext)
            }
            private fun dowloadXML(urlPath: String?):String{
                try{
                    return URL(urlPath).readText()
                }catch (e: Exeption){
                    val errorMessage: String = when (e){
                        is MalformedURLException -> "downloadXML: Invalid URL ${e.message}"
                        is IOException -> "downloadXML: IOException reading data ${e.message}"
                        else -> "downloadXML: Unknown error ${e.message}"
                    }
                    Log.e(TAG, errorMessage)
                }
                return ""
            }

        }
    }
}