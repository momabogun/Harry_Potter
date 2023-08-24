package com.example.premierleaguefootball.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.premierleaguefootball.data.AppRepository
import com.example.premierleaguefootball.data.remote.TeamApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TeamViewModel:ViewModel() {

    private val repository = AppRepository(TeamApi.retrofitService)

    val teams = repository.teams


    init {
        loadTeams()
    }

    fun loadTeams(){

        viewModelScope.launch(Dispatchers.IO) {
            repository.getTeams()
        }
    }




}