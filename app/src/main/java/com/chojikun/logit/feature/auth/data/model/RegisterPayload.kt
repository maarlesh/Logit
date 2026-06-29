package com.chojikun.logit.feature.auth.data.model

data class RegisterPayload(
    val email: String,
    val authKey: String,
    val kdfSalt: String,
    val kdfParams: KdfParams,
    val wrappedVaultKey: String,
    val vaultKeyNonce: String,
)

data class KdfParams(
    val memory: Int,
    val iterations: Int,
    val parallelism: Int,
)
