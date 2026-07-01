package com.chojikun.logit.feature.auth.data.model

import com.chojikun.logit.feature.auth.domain.model.KdfParams

data class LoginData(
    val accessToken: String,
    val refreshToken: String,
    val kdfSalt: String,
    val kdfParams: KdfParams,
    val wrappedVaultKey: String,
    val vaultKeyNonce: String,
)
