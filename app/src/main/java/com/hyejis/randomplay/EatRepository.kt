package com.hyejis.randomplay

import androidx.lifecycle.LiveData
import com.hyejis.randomplay.dao.AppDatabase
import com.hyejis.randomplay.dao.EatDao
import com.hyejis.randomplay.todayeat.EatList

class EatRepository(mDatabase: AppDatabase) {

    private val eatDao = mDatabase.eatDao()
    val allUsers: LiveData<List<EatList>> = eatDao.getAll()

    companion object{
        private var sInstance: EatRepository? = null
        fun getInstance(database: AppDatabase): EatRepository {
            return sInstance
                ?: synchronized(this){
                    val instance = EatRepository(database)
                    sInstance = instance
                    instance
                }
        }
    }
    suspend fun insert(eatList: EatList) {
        eatDao.insert(eatList)
    }

    suspend fun delete(eatList: EatList) {
        eatDao.delete(eatList)
    }

    fun getAll(): LiveData<List<EatList>> {
        return eatDao.getAll()
    }
}