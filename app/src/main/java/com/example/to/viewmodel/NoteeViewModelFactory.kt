package com.example.to.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.to.repository.NoteeRepository

class NoteeViewModelFactory(val app:Application, private val noteeRepository: NoteeRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return NoteeViewModel(app, noteeRepository) as T
    }
}