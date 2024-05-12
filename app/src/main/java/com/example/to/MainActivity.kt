package com.example.to

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.to.database.NoteeDatabase
import com.example.to.repository.NoteeRepository
import com.example.to.viewmodel.NoteeViewModel
import com.example.to.viewmodel.NoteeViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var noteeViewModel: NoteeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
    }

    private fun setupViewModel(){
        val noteeRepository = NoteeRepository(NoteeDatabase(this))
        val viewModelProviderFactory = NoteeViewModelFactory(application, noteeRepository)
        noteeViewModel = ViewModelProvider(this, viewModelProviderFactory)[NoteeViewModel::class.java]
    }
}