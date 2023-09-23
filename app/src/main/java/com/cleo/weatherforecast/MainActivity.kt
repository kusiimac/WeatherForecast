package com.cleo.weatherforecast

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.cleo.weatherforecast.data.RetrofitInstance
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.Exception

class MainActivity : AppCompatActivity() {

    private lateinit var enteredCity:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val inputCity = findViewById<EditText>(R.id.search_city)
        val search = findViewById<Button>(R.id.button)

        if (isNetworkConnected(this@MainActivity)) {
            //Connected to the internet

            CoroutineScope(Dispatchers.IO).launch {


                val service = RetrofitInstance().createRetrofit()
                val apiResponse =
                    service.getWeather("Mengo", "bb0e8f9d5e3482f3405d034d27d7cdd0", "metric")

                withContext(Dispatchers.Main) {

                    findViewById<TextView>(R.id.city).text = apiResponse.name

                    findViewById<TextView>(R.id.weather).text = "${apiResponse.weather[0].main}"
                    findViewById<TextView>(R.id.temperature).text =
                        "${apiResponse.main.temp.toLong()}⁰C"
                    findViewById<TextView>(R.id.min_temp).text =
                        "Min: ${apiResponse.main.temp_min.toLong()}⁰C"
                    findViewById<TextView>(R.id.max_temp).text =
                        "Max: ${apiResponse.main.temp_max.toLong()}⁰C"
                    findViewById<TextView>(R.id.humidity).text =
                        "Humidity\n${apiResponse.main.humidity}%"
                    findViewById<TextView>(R.id.pressure).text =
                        "Pressure\n${apiResponse.main.pressure} hPa"
                    findViewById<TextView>(R.id.wind).text =
                        "Wind\n${apiResponse.wind.speed} m/s"
                    findViewById<TextView>(R.id.description).text =
                        "${apiResponse.weather[0].description}"
                }
            }
        } else {
            Toast.makeText(this@MainActivity, "No Internet Connection", Toast.LENGTH_LONG).show()
        }


        search.setOnClickListener {

            enteredCity = inputCity.text.toString()

            if (enteredCity == "") {

                Toast.makeText(
                    this@MainActivity,
                    "Please enter a valid city name",
                    Toast.LENGTH_SHORT
                ).show()
            } else {

                val inputMethodManager =
                    getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(inputCity.getWindowToken(), 0)

                inputCity.text.clear()


                if (isNetworkConnected(this@MainActivity)) {
                    //Connected to the internet

                    CoroutineScope(Dispatchers.IO).launch {

                        try {

                            val service = RetrofitInstance().createRetrofit()
                            val apiResponse =
                                service.getWeather(
                                    "$enteredCity",
                                    "bb0e8f9d5e3482f3405d034d27d7cdd0",
                                    "metric"
                                )

                            withContext(Dispatchers.Main) {

                                findViewById<TextView>(R.id.city).text = apiResponse.name

                                findViewById<TextView>(R.id.weather).text =
                                    "${apiResponse.weather[0].main}"
                                findViewById<TextView>(R.id.temperature).text =
                                    "${apiResponse.main.temp.toLong()}⁰C"
                                findViewById<TextView>(R.id.min_temp).text =
                                    "Min: ${apiResponse.main.temp_min.toLong()}⁰C"
                                findViewById<TextView>(R.id.max_temp).text =
                                    "Max: ${apiResponse.main.temp_max.toLong()}⁰C"
                                findViewById<TextView>(R.id.humidity).text =
                                    "Humidity\n${apiResponse.main.humidity}%"
                                findViewById<TextView>(R.id.pressure).text =
                                    "Pressure\n${apiResponse.main.pressure} hPa"
                                findViewById<TextView>(R.id.wind).text =
                                    "Wind\n${apiResponse.wind.speed} m/s"
                                findViewById<TextView>(R.id.description).text =
                                    "${apiResponse.weather[0].description}"
                            }

                        } catch (e: Throwable) {
                            return@launch
                        }
                    }
                } else {
                    Toast.makeText(this@MainActivity, "No Internet Connection", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }
}