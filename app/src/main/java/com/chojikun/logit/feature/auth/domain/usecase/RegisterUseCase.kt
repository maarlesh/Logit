package com.chojikun.logit.feature.auth.domain.usecase

import com.chojikun.logit.core.network.util.ApiResult
import com.chojikun.logit.core.util.CryptoHelper
import com.chojikun.logit.feature.auth.domain.model.RegisterResult
import com.chojikun.logit.feature.auth.domain.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: AuthRepository,
) {
    suspend operator fun invoke(email: String, password: String): ApiResult<RegisterResult> {
        val payload = withContext(Dispatchers.Default) {
            CryptoHelper.prepareRegisterPayload(email, password)
        }
        return repository.register(payload)
    }
}
