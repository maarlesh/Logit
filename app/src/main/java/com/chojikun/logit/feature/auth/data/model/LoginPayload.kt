package com.chojikun.logit.feature.auth.data.model

data class LoginPayload(
    val email: String,
    val authKey: String,
)