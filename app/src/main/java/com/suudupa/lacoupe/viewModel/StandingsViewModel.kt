package com.suudupa.lacoupe.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StandingsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Player Standings"
    }
    val text: LiveData<String> = _text
}