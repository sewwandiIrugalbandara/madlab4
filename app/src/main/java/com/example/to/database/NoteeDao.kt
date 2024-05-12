package com.example.to.database

import android.provider.ContactsContract.CommonDataKinds.Note
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.to.model.Notee

@Dao
interface NoteeDao {
    @Insert(onConflict =OnConflictStrategy.REPLACE)
    suspend fun insertNote(notee: Notee)

    @Update
    suspend fun updateNote(notee: Notee)

    @Delete
    suspend fun deleteNote(notee: Notee)

    @Query("SELECT * FROM NOTES ORDER BY id DESC")
    fun getAllNotes(): LiveData<List<Notee>>

    @Query("SELECT* FROM NOTES WHERE noteTitle LIKE :query OR noteDesc LIKE :query")
    fun searchNote(query: String?): LiveData<List<Notee>>
}