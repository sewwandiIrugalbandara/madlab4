package com.example.to.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.to.model.Notee
import java.util.concurrent.locks.Lock

@Database(entities = [Notee::class], version = 1)
abstract class NoteeDatabase: RoomDatabase() {

    abstract fun getNoteeDao(): NoteeDao

    companion object{
        @Volatile
        private var instance: NoteeDatabase? = null
        private val Lock = Any()

        operator fun invoke(context: Context) = instance ?:
        synchronized(Lock){
            instance ?:
            createDatabase(context).also{
                instance = it
            }
        }

        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                NoteeDatabase::class.java,
                "notee_db"
            ).build()

    }
}