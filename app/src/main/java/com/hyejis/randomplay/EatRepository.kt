package com.hyejis.randomplay

import android.app.Application
import androidx.lifecycle.LiveData
import com.hyejis.randomplay.dao.AppDatabase
import com.hyejis.randomplay.dao.EatDao
import com.hyejis.randomplay.todayeat.EatList

class EatRepository(application: Application) {
    private val eatDao: EatDao
    private val eatList: LiveData<List<EatList>>

    init {
        var db = AppDatabase.getInstance(application)
        eatDao = db!!.eatDao()
        eatList = db.eatDao().getAll()
    }

    fun insert(eatList: EatList) {
        eatDao.insert(eatList)
    }

    fun delete(eatList: EatList){
        eatDao.delete(eatList)
    }

    fun getAll(): LiveData<List<EatList>> {
        return eatDao.getAll()
    }
}