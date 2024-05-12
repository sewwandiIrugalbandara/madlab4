package com.example.to.repository

import com.example.to.database.NoteeDatabase
import com.example.to.model.Notee

class NoteeRepository(private val db: NoteeDatabase) {

    suspend fun insertNotee(notee: Notee) = db.getNoteeDao().insertNote(notee)
    suspend fun deleteNotee(notee: Notee) = db.getNoteeDao().deleteNote(notee)
    suspend fun updateNotee(notee: Notee) = db.getNoteeDao().updateNote(notee)

    fun getAllNotes() = db.getNoteeDao().getAllNotes()
    fun searchNotes(query: String) = db.getNoteeDao().searchNote(query)
}