package com.example.retrofit11

import retrofit2.Call
import retrofit2.http.GET

interface Service {

    //https://jsonplaceholder.typicode.com/users

    @GET("users")
    fun getUser(): Call<List<User>>


}