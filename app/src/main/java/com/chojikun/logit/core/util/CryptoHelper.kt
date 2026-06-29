package com.chojikun.logit.core.util

import android.util.Base64
import com.chojikun.logit.feature.auth.data.model.KdfParams
import com.chojikun.logit.feature.auth.data.model.RegisterPayload
import com.lambdapioneer.argon2kt.Argon2Kt
import com.lambdapioneer.argon2kt.Argon2Mode
import java.security.SecureRandom
import javax.crypto.Cipher
import javax.crypto.spec.GCMParameterSpec
import javax.crypto.spec.SecretKeySpec

object CryptoHelper {

    private const val KDF_MEMORY   = 65536
    private const val KDF_ITERS    = 3
    private const val KDF_PARALLEL = 4

    fun prepareRegisterPayload(email: String, password: String): RegisterPayload {
        // Step 1: random 16-byte salt for Argon2
        val salt = ByteArray(16).also { SecureRandom().nextBytes(it) }

        // Step 2: Argon2id → 64 bytes
        // First 32 bytes = vaultKeyEncKey (never leaves device)
        // Last  32 bytes = authKey        (sent to server for authentication)
        val argon2 = Argon2Kt()
        val keyBytes = argon2.hash(
            mode = Argon2Mode.ARGON2_ID,
            password = password.toByteArray(Charsets.UTF_8),
            salt = salt,
            tCostInIterations = KDF_ITERS,
            mCostInKibibyte = KDF_MEMORY,
            parallelism = KDF_PARALLEL,
            hashLengthInBytes = 64,
        ).rawHashAsByteArray()

        val vaultKeyEncKey = keyBytes.copyOfRange(0, 32)
        val authKey        = keyBytes.copyOfRange(32, 64)

        // Step 3: random 32-byte vault key — the actual symmetric key for encrypting user data
        val vaultKey = ByteArray(32).also { SecureRandom().nextBytes(it) }

        // Step 4: wrap vault key with AES-256-GCM using vaultKeyEncKey
        val nonce = ByteArray(12).also { SecureRandom().nextBytes(it) }
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(
            Cipher.ENCRYPT_MODE,
            SecretKeySpec(vaultKeyEncKey, "AES"),
            GCMParameterSpec(128, nonce),
        )
        val wrappedVaultKey = cipher.doFinal(vaultKey)

        return RegisterPayload(
            email           = email,
            authKey         = authKey.toHex(),
            kdfSalt         = Base64.encodeToString(salt, Base64.NO_WRAP),
            kdfParams       = KdfParams(KDF_MEMORY, KDF_ITERS, KDF_PARALLEL),
            wrappedVaultKey = Base64.encodeToString(wrappedVaultKey, Base64.NO_WRAP),
            vaultKeyNonce   = Base64.encodeToString(nonce, Base64.NO_WRAP),
        )
    }

    private fun ByteArray.toHex() = joinToString("") { "%02x".format(it) }
}
