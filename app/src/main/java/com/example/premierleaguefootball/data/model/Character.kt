package com.example.premierleaguefootball.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Character(
    @PrimaryKey
    val id: String,
    val name: String,
    val image: String,
    val species: String,
    val house: String,
    val ancestry: String,
    val actor: String
) {
}