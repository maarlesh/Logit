package com.chojikun.logit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chojikun.logit.util.FirstLaunchManager
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val firstLaunchManager: FirstLaunchManager
) : ViewModel() {

    val isFirstLaunch: StateFlow<Boolean?> = firstLaunchManager.isFirstLaunch
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), null)

    fun onEntryScreenDone() {
        viewModelScope.launch {
            firstLaunchManager.markLaunched()
        }
    }
}
