package com.hyejis.randomplay.todayeat

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EatList(
    val category : String, val food : String
){
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}