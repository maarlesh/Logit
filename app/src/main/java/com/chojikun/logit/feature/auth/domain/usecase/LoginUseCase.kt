package com.chojikun.logit.feature.auth.domain.usecase

import com.chojikun.logit.core.network.util.ApiResult
import com.chojikun.logit.core.util.CryptoHelper
import com.chojikun.logit.feature.auth.data.model.LoginPayload
import com.chojikun.logit.feature.auth.domain.model.KdfParams
import com.chojikun.logit.feature.auth.domain.model.LoginResult
import com.chojikun.logit.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        kdfSalt: String,
        kdfParams: KdfParams,
    ): ApiResult<LoginResult> {
        val authKey = withContext(Dispatchers.Default) {
            CryptoHelper.deriveAuthKey(password, kdfSalt, kdfParams)
        }
        return repository.login(LoginPayload(email, authKey))
    }
}
