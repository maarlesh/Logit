package com.chojikun.logit.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.chojikun.logit.util.FirstLaunchManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val firstLaunchManager = FirstLaunchManager(application)

    val isFirstLaunch: StateFlow<Boolean?> = firstLaunchManager.isFirstLaunch
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun onEntryScreenDone() {
        viewModelScope.launch {
            firstLaunchManager.markLaunched()
        }
    }
}
