package com.hyejis.randomplay.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Dao
import com.hyejis.randomplay.todayeat.EatList

@Dao
interface EatDao {
    @Query("SELECT * FROM EatList")
    fun getAll(): LiveData<List<EatList>>

    @Query("SELECT * FROM EatList WHERE category = :category")
    fun getFoodCategory(category: String): LiveData<List<EatList>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(eatList: EatList)

    @Update
    fun update(eatList: EatList)

    @Delete
    suspend fun delete(eatList: EatList)
}