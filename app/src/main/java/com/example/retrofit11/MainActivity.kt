package com.example.retrofit11

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    lateinit var text :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text= findViewById(R.id.text)


        //https://api.themoviedb.org/3/movie/popular?api_key=19e9185cec4a98676856644d3e66cc2c&language=en-US&page=1

        //https://jsonplaceholder.typicode.com/users


        val retrofit  = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()

        val Service = retrofit.create(Service::class.java)

        val call : Call<List<User>> = Service.getUser()
        call.enqueue(object : Callback<List<User>> {
            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
                val Users:List<User> = response.body()!!

                val StringBuilder = StringBuilder()
                for(user in Users){
                    StringBuilder.append(user.name)
                }
                text.text=StringBuilder



            }

            override fun onFailure(call: Call<List<User>>, t: Throwable) {
               Log.e("error",t.toString())
            }

        })
    }


}