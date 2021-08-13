package com.example.basicretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.*

val BASE_URL : String = "https://api.themoviedb.org"
val PAGE: Int = 1;
val API_KEY: String = "cbd4b92662bd89da04fdd8e3e2fef7cb"
val LANGUAGE: String = "en-US"
val CATEGORY: String = "popular"
class MainActivity : AppCompatActivity() {

    lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()

        val apiInterface: ApiInterface = retrofit.create(ApiInterface::class.java)
        val call: Call<String> = apiInterface.getMovies(CATEGORY, API_KEY, LANGUAGE, PAGE)

        call.enqueue(object : Callback<String>{
            override fun onResponse(call: Call<String>, response: Response<String>) {
                textView.text = response.body()
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                textView.text = "Failure"+ t.message
            }

        })

    }
}