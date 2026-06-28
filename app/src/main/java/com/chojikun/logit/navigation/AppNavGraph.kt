package com.chojikun.logit.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.chojikun.logit.ui.feature.onboarding.EntryScreen
import com.chojikun.logit.viewmodel.MainViewModel

@Composable
fun AppNavGraph(modifier: Modifier = Modifier) {
    val viewModel: MainViewModel = hiltViewModel()
    val isFirstLaunch by viewModel.isFirstLaunch.collectAsStateWithLifecycle()
    val navController = rememberNavController()

    if (isFirstLaunch == null) return

    val startDestination = if (isFirstLaunch == true) Routes.ENTRY else Routes.LOGIN

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Routes.ENTRY) {
            EntryScreen(
                onGettingStarted = {
                    viewModel.onEntryScreenDone()
                    navController.navigate(Routes.ENTRY) {
                        popUpTo(Routes.ENTRY) { inclusive = true }
                    }
                },
                onSigningInTapped = {
                    navController.navigate(Routes.LOGIN)
                }
            )
        }
        composable("home") {
            Text(text = "Home Screen")
        }
        composable(
            Routes.LOGIN
        ) {
            Text(text = "Sign In Screen")
        }
    }
}
