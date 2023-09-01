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

enum class LoadingStatus {
    LOADING,
    DONE,
    ERROR
}

val TAG = "ViewModel"

class CharViewModel(application: Application) : AndroidViewModel(application) {


    private val database = getDatabase(application)
    private val repository = AppRepository(CharApi, database)


    private val _loading = MutableLiveData<LoadingStatus>()
    val loading: LiveData<LoadingStatus>
        get() = _loading


    val chars = repository.charList


    fun deleteChar(character: Character){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteChar(character)
        }
    }

    fun getChar(charId: String): LiveData<Character> = repository.getChar(charId)




    fun loadChars() {
        viewModelScope.launch {
            _loading.value = LoadingStatus.LOADING
            try {
                repository.getChars()
                _loading.value = LoadingStatus.DONE
            } catch (e: Exception) {
                Log.e(TAG, "Error $e")
                if (chars.value.isNullOrEmpty()) {
                    _loading.value = LoadingStatus.ERROR
                } else {
                    _loading.value = LoadingStatus.DONE
                }

            }
        }


    }
}