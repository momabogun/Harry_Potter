package com.example.premierleaguefootball.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.premierleaguefootball.data.model.Character

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(memes: List<Character>)

    @Update
    suspend fun update(character: Character)

    @Query("SELECT * FROM character WHERE id =:charId")
    fun getCharById(charId: String) : LiveData<Character>


    @Delete
    suspend fun delete(character: Character)

    @Query("SELECT * from character")
    fun getAllChars(): LiveData<List<Character>>













}