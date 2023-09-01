package com.example.premierleaguefootball.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.premierleaguefootball.data.AppRepository
import com.example.premierleaguefootball.data.db.getDatabase
import com.example.premierleaguefootball.data.model.Character
import com.example.premierleaguefootball.data.remote.CharApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CharViewModel(application: Application) : AndroidViewModel(application) {


    private val database = getDatabase(application)
    private val repository = AppRepository(CharApi, database)


    val chars = repository.charList


    fun deleteChar(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteChar(character)
        }
    }

    fun getChar(charId: String): LiveData<Character> = repository.getChar(charId)


    fun loadChars() {
        viewModelScope.launch {
            repository.getChars()

        }
    }


}
