package com.example.appdyntest

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

interface DummyService  {
    companion object {
        fun getInstance() : DummyService {
            return Retrofit.Builder()
                .addConverterFactory(ScalarsConverterFactory.create())
                .baseUrl("https://httbin.org") // A real service that replies with 200
                .build()
                .create(DummyService::class.java)
        }
    }

    @GET("/get")
    fun doGet(): Call<String>
}

