package com.hyejis.randomplay.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.hyejis.randomplay.todayeat.EatList

@Dao
interface EatDao {
    @Query("SELECT * FROM EatList")
    fun getAll(): LiveData<List<EatList>>

    @Query("SELECT * FROM EatList WHERE category")
    fun getFoodCategory(): LiveData<List<EatList>>

    @Insert
    fun insert(eatList: EatList)

    @Update
    fun update(eatList: EatList)

    @Delete
    fun delete(eatList: EatList)
}