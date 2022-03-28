package com.hyejis.randomplay

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.hyejis.randomplay.dao.AppDatabase
import com.hyejis.randomplay.todayeat.EatList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EatViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = EatRepository(AppDatabase.getDatabase(application, viewModelScope))
    val items = repository.getAll()

    var result: ObservableField<String> = ObservableField("Main")
    val mApplication = application

    class Factory(val application: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return EatViewModel(application) as T
        }
    }

    fun insert(eatList: EatList) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(eatList)
    }

    fun delete(eatList: EatList) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(eatList)
    }

    fun getAll(): LiveData<List<EatList>> {
        return items
    }

    fun onClickButton() {
        // TODO: Click 시 Room에 데이터를 가져와서 보여줌
        Toast.makeText(mApplication, "Click!", Toast.LENGTH_SHORT).show()
    }

}