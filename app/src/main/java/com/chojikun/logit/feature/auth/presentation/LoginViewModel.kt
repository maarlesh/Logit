package com.chojikun.logit.feature.auth.presentation

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.chojikun.logit.core.util.CryptoHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor() : ViewModel() {

    var email by mutableStateOf("")
        private set
    var emailError by mutableStateOf<String?>(null)
        private set

    var password by mutableStateOf("")
        private set
    var passwordError by mutableStateOf<String?>(null)
        private set

    var confirmPassword by mutableStateOf("")
        private set
    var confirmPasswordError by mutableStateOf<String?>(null)
        private set

    fun onEmailChange(value: String) {
        email = value
        emailError = validateEmailOrNull(value)
    }

    fun onPasswordChange(value: String) {
        password = value
        passwordError = validatePasswordOrNull(value)
        if (confirmPassword.isNotEmpty()) {
            confirmPasswordError = validateConfirmOrNull(confirmPassword, value)
        }
    }

    fun onConfirmPasswordChange(value: String) {
        confirmPassword = value
        confirmPasswordError = validateConfirmOrNull(value, password)
    }

    fun onRegisterClick() {
        if (!validateAll()) return
        val payload = CryptoHelper.prepareRegisterPayload(email, password)
        Log.d("LoginViewModel", "Register payload: $payload")
        // TODO: pass payload to AuthRepository once the network layer is set up
    }

    private fun validateAll(): Boolean {
        emailError           = validateEmailOrNull(email)
        passwordError        = validatePasswordOrNull(password)
        confirmPasswordError = validateConfirmOrNull(confirmPassword, password)
        return emailError == null && passwordError == null && confirmPasswordError == null
    }

    private fun validateEmailOrNull(value: String): String? = when {
        value.isBlank() -> "Email is required"
        !Patterns.EMAIL_ADDRESS.matcher(value).matches() -> "Enter a valid email address"
        else -> null
    }

    private fun validatePasswordOrNull(value: String): String? = when {
        value.isBlank() -> "Password is required"
        value.length <= 5 -> "Password must be at least 6 characters"
        !value.matches(Regex("^[a-zA-Z0-9]+\$")) -> "Password must be alphanumeric (letters and numbers only)"
        else -> null
    }

    private fun validateConfirmOrNull(value: String, against: String): String? = when {
        value.isBlank() -> "Please confirm your password"
        value != against -> "Passwords do not match"
        else -> null
    }
}
