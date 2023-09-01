package com.example.premierleaguefootball.data.remote

import com.example.premierleaguefootball.data.model.Character
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

const val BASE_URL = "https://hp-api.onrender.com/api/"


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CharApiService {

    @GET("characters")
    suspend fun getChars(): List<Character>
}

object CharApi {
    val retrofitService: CharApiService by lazy { retrofit.create(CharApiService::class.java) }
}