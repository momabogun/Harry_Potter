package com.example.premierleaguefootball.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.premierleaguefootball.data.model.Character

@Database(entities = [Character::class], version = 1)
abstract class CharacterDatabase: RoomDatabase() {

    abstract val characterDao: CharacterDao
}


private lateinit var INSTANCE: CharacterDatabase



fun getDatabase(context: Context): CharacterDatabase {
    synchronized(CharacterDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                CharacterDatabase::class.java,
                "character_database"
            ).build()
        }
    }
    return INSTANCE
}