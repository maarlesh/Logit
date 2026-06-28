package com.chojikun.logit.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chojikun.logit.ui.feature.onboarding.EntryScreen
import com.chojikun.logit.viewmodel.MainViewModel

@Composable
fun AppNavGraph() {
    val viewModel: MainViewModel = viewModel()
    val isFirstLaunch by viewModel.isFirstLaunch.collectAsStateWithLifecycle()
    val navController = rememberNavController()

    if (isFirstLaunch == null) return

    val startDestination = if (isFirstLaunch == true) "entry" else "home"

    NavHost(navController = navController, startDestination = startDestination) {
        composable("entry") {
            EntryScreen()
        }
        composable("home") {
            Text(text = "Home Screen")
        }
    }
}
