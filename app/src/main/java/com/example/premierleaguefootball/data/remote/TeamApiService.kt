package com.example.premierleaguefootball.data.remote

import com.example.premierleaguefootball.data.model.Team
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

interface TeamApiService {
    @GET("characters")
    suspend fun getTeams(): List<Team>
}

object TeamApi {
    val retrofitService: TeamApiService by lazy { retrofit.create(TeamApiService::class.java) }
}