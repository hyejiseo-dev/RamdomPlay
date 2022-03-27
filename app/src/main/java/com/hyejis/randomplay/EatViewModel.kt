package com.hyejis.randomplay

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.hyejis.randomplay.todayeat.EatList

class EatViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = EatRepository(application)
    private val items = repository.getAll()

    fun insert(eatList: EatList) {
        repository.insert(eatList)
    }

    fun delete(eatList: EatList){
        repository.delete(eatList)
    }

    fun getAll(): LiveData<List<EatList>> {
        return items
    }
}