package br.tales.businesscard.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Card
    (
    @PrimaryKey(autoGenerate = true) val cardPK: Int = 0,
    val cardName: String,
    val cardCompany: String,
    val cardPhone: String,
    val cardEmail : String,
    val cardColor: String
    )