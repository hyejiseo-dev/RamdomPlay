package com.hyejis.randomplay.dao

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.hyejis.randomplay.todayeat.EatList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [EatList::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eatDao(): EatDao

    private val mIsDatabaseCreated = MutableLiveData<Boolean>()
    companion object {
        private var INSTANCE: AppDatabase? = null
        private const val DATABASE_NAME = "app_database"
        fun getDatabase(
            context: Application,
            scope: CoroutineScope
        ): AppDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                INSTANCE?.updateDatabaseCreated(context.applicationContext)
                // return instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.eatDao())
                }
            }
        }
        suspend fun populateDatabase(eatDao: EatDao) {

            //userDao.deleteAll()
            // Add User
            eatDao.insert(EatList("test","음식"))
        }
    }

    /**
     * Check whether the database already exists and expose it via [.getDatabaseCreated]
     */
    private fun updateDatabaseCreated(context: Context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated()
        }
    }

    private fun setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true)
    }

    open fun getDatabaseCreated(): LiveData<Boolean> {
        return mIsDatabaseCreated
    }

}