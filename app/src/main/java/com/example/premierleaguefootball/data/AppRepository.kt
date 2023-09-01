package com.example.premierleaguefootball.data

import androidx.lifecycle.LiveData
import com.example.premierleaguefootball.data.db.CharacterDatabase
import com.example.premierleaguefootball.data.model.Character
import com.example.premierleaguefootball.data.remote.CharApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

const val TAG = "AppRepositoryTAG"
class AppRepository(private val api: CharApi, private val database: CharacterDatabase) {

    val charList: LiveData<List<Character>> = database.characterDao.getAllChars()

    suspend fun deleteChar(character: Character){
        database.characterDao.delete(character.id)
    }

    fun getChar(charId:String): LiveData<Character> = database.characterDao.getCharById(charId)


    suspend fun getChars(){
        withContext(Dispatchers.IO){
            val newCharList = api.retrofitService.getChars()
            database.characterDao.insertAll(newCharList)
        }
    }

}