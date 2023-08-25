package com.example.premierleaguefootball.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.premierleaguefootball.data.model.Team
import com.example.premierleaguefootball.data.remote.TeamApiService
import kotlinx.coroutines.delay
import java.lang.Exception
const val TAG = "AppRepositoryTAG"
class AppRepository(private val api: TeamApiService) {

    private val _teams = MutableLiveData<List<Team>>()
    val teams: LiveData<List<Team>>
        get() = _teams

    suspend fun getTeams(){
        try {
            val teamList = api.getTeams()
            _teams.postValue(teamList)

        } catch (e: Exception){
            Log.e(TAG,"Error loading Data from API: $e")
        }
    }

}