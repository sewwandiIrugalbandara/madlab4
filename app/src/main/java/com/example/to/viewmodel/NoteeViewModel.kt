package com.example.to.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Query
import com.example.to.model.Notee
import com.example.to.repository.NoteeRepository
import kotlinx.coroutines.launch

class NoteeViewModel(app: Application,private val noteeRepository: NoteeRepository): AndroidViewModel(app) {

    fun addNote(notee: Notee) =
        viewModelScope.launch {
            noteeRepository.insertNotee(notee)
        }

    fun deleteNote(notee: Notee) =
        viewModelScope.launch {
            noteeRepository.deleteNotee(notee)
        }

    fun updateNote(notee: Notee) =
        viewModelScope.launch {
            noteeRepository.updateNotee(notee)
        }

    fun getAllNotes() = noteeRepository.getAllNotes()

    fun searchNote(query: String?) =
        query?.let { noteeRepository.searchNotes(it) }
}